package com.mutool.box.plugin;

import com.mutool.box.model.PluginJarInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddPluginResult {

    private PluginJarInfo pluginJarInfo;

    private boolean newPlugin;
}
