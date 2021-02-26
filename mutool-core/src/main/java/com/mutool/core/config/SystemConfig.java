package com.mutool.core.config;


/**
 * 描述：系统设置<br>
 * 作者：les<br>
 * 日期：2020/11/28 14:14<br>
 */
public class SystemConfig {

    /**
     * 服务域名
     */
    private static String serverDoamin;

    /**
     * 服务端口
     */
    private static String serverPort;

    public static String getServerDoamin() {
        return serverDoamin;
    }

    public static String getServerPort() {
        return serverPort;
    }

    public static String getServerUrl() {
        return serverDoamin + ":" + serverPort;
    }

}
