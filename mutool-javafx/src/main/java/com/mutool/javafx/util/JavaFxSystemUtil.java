package com.mutool.javafx.util;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

//@Slf4j
public class JavaFxSystemUtil {

    /**
     * @deprecated 使用 {@link FxApp#primaryStage}
     */
    @Deprecated
    public static Stage mainStage = null;

    /**
     * 打开目录
     *
     * @param directoryPath 目录路径
     */
    public static void openDirectory(String directoryPath) {
        try {
            Desktop.getDesktop().open(new File(directoryPath));
        } catch (IOException e) {
            //log.error("打开目录异常：" + directoryPath, e);
            e.printStackTrace();
        }
    }


    /**
     * 获取屏幕尺寸
     *
     * @param width  宽度比
     * @param height 高度比
     * @return 屏幕尺寸
     */
    public static double[] getScreenSizeByScale(double width, double height) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.width * width;
        double screenHeight = screenSize.height * height;
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        if (screenWidth > bounds.getWidth() || screenHeight > bounds.getHeight()) {//解决屏幕缩放问题
            screenWidth = bounds.getWidth();
            screenHeight = bounds.getHeight();
        }
        return new double[]{screenWidth, screenHeight};
    }
}
