package com.mutool.box.controller.index;

import com.mutool.box.controller.IndexController;
import com.mutool.box.event.AppEvents;
import com.mutool.box.event.PluginEvent;
import com.mutool.box.model.PluginJarInfo;
import com.mutool.box.plugin.AddPluginResult;
import com.mutool.box.plugin.PluginClassLoader;
import com.mutool.box.plugin.PluginManager;
import com.mutool.box.plugin.PluginParser;
import com.mutool.box.services.index.PluginManageService;
import com.mutool.box.view.index.PluginManageView;
import com.xwintop.xcore.util.javafx.FileChooserUtil;
import com.xwintop.xcore.util.javafx.JavaFxViewUtil;
import com.xwintop.xcore.util.javafx.TooltipUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import javafx.util.Callback;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/**
 * 插件管理
 *
 * @author xufeng
 * @since 2020/1/19 17:41
 */

@Getter
@Setter
@Slf4j
public class PluginManageController extends PluginManageView {

    public static final String FXML = "/fxmlView/index/PluginManage.fxml";

    private PluginManageService pluginManageService = new PluginManageService(this);

    private ObservableList<Map<String, String>> originPluginData = FXCollections.observableArrayList();

    private FilteredList<Map<String, String>> pluginDataTableData = new FilteredList<>(originPluginData, m -> true);

    public static FXMLLoader getFXMLLoader() {
        return new FXMLLoader(IndexController.class.getResource(FXML));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initView();
        initEvent();
        initService();
    }

    public Window getWindow() {
        return this.pluginDataTableView.getScene().getWindow();
    }

    public void setOnPluginDownloaded(Consumer<File> onPluginDownloaded) {
        this.pluginManageService.setOnPluginDownloaded(onPluginDownloaded);
    }

    private void initView() {
        addLocalPluginButton.setVisible(Boolean.parseBoolean(System.getProperty("localPluginEnabled", "false")));

        JavaFxViewUtil.setTableColumnMapValueFactory(nameTableColumn, "nameTableColumn");
        JavaFxViewUtil.setTableColumnMapValueFactory(synopsisTableColumn, "synopsisTableColumn");
        JavaFxViewUtil.setTableColumnMapValueFactory(versionTableColumn, "versionTableColumn");
        JavaFxViewUtil.setTableColumnMapValueFactory(isDownloadTableColumn, "isDownloadTableColumn");
        JavaFxViewUtil.setTableColumnMapAsCheckBoxValueFactory(isEnableTableColumn, "isEnableTableColumn",
            (mouseEvent, index) -> pluginManageService.setIsEnableTableColumn(index)
        );

        // TODO 实现插件的启用禁用

        downloadTableColumn.setCellFactory(
            new Callback<TableColumn<Map<String, String>, String>, TableCell<Map<String, String>, String>>() {
                @Override
                public TableCell<Map<String, String>, String> call(TableColumn<Map<String, String>, String> param) {
                    return new TableCell<Map<String, String>, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            this.setText(null);
                            this.setGraphic(null);
                            if (!empty) {
                                Map<String, String> dataRow = pluginDataTableData.get(this.getIndex());
                                Button downloadButton = new Button(dataRow.get("isDownloadTableColumn"));
                                if ("已下载".equals(dataRow.get("isDownloadTableColumn"))) {
                                    downloadButton.setDisable(true);
                                }
                                this.setContentDisplay(ContentDisplay.CENTER);
                                downloadButton.setOnMouseClicked(event -> downloadPlugin(dataRow, downloadButton));
                                this.setGraphic(downloadButton);
                            }
                        }
                    };
                }
            });

        pluginDataTableView.setItems(pluginDataTableData);
    }

    private void downloadPlugin(Map<String, String> dataRow, Button downloadButton) {
        try {
            PluginJarInfo pluginJarInfo = pluginManageService.downloadPluginJar(dataRow);
            afterDownload(dataRow, downloadButton, pluginJarInfo);
        } catch (Exception e) {
            log.error("下载插件失败：", e);
            TooltipUtil.showToast("下载插件失败：" + e.getMessage());
        }
    }

    private void afterDownload(
        Map<String, String> dataRow, Button downloadButton, PluginJarInfo pluginJarInfo
    ) throws IOException {

        dataRow.put("isEnableTableColumn", "true");
        dataRow.put("isDownloadTableColumn", "已下载");

        downloadButton.setText("已下载");
        downloadButton.setDisable(true);

        pluginDataTableView.refresh();
        PluginManager.getInstance().saveToFile();
        TooltipUtil.showToast("插件 " + dataRow.get("nameTableColumn") + " 下载完成");

        PluginClassLoader tempClassLoader = new PluginClassLoader(pluginJarInfo.getFile());
        PluginParser.parse(pluginJarInfo.getFile(), pluginJarInfo, tempClassLoader);

        AppEvents.fire(new PluginEvent(PluginEvent.PLUGIN_DOWNLOADED, pluginJarInfo));
    }

    private void initEvent() {

        // 右键菜单
        MenuItem mnuSavePluginConfig = new MenuItem("保存配置");
        mnuSavePluginConfig.setOnAction(ev -> {
            try {
                PluginManager.getInstance().saveToFile();
                TooltipUtil.showToast("保存配置成功");
            } catch (Exception ex) {
                log.error("保存插件配置失败", ex);
            }
        });

        ContextMenu contextMenu = new ContextMenu(mnuSavePluginConfig);
        pluginDataTableView.setContextMenu(contextMenu);

        // 搜索
        selectPluginTextField.textProperty().addListener((_ob, _old, _new) -> {
            pluginManageService.searchPlugin(_new);
        });
    }

    private void initService() {
        pluginManageService.getPluginList();
    }

    public void searchPlugin() {
        pluginManageService.searchPlugin(selectPluginTextField.getText());
    }

    public void addLocalPlugin() {
        File jarFile = FileChooserUtil.chooseFile(new ExtensionFilter("打包插件(*.jar)", "*.jar"));
        if (jarFile != null) {
            AddPluginResult result = PluginManager.getInstance().addPluginJar(jarFile);
            if (result.isNewPlugin()) {
                pluginManageService.addDataRow(result.getPluginJarInfo());
            }
            AppEvents.fire(new PluginEvent(PluginEvent.PLUGIN_DOWNLOADED, result.getPluginJarInfo()));
        }
    }
}