package com.mutool.core.util;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.util.Map;

/**
 * @ClassName: SystemInfoUtil
 * @Description: 获取系统信息工具类
 * @author: xufeng
 * @date: 2018/1/31 15:09
 */

public class SystemInfoUtil {

    private static final Log log = LogFactory.get();

    /**
     * 获取Hosts文件路径
     */
    public static String getHostsFilePath() {
        String fileName = null;
        // 判断系统
        if ("linux".equalsIgnoreCase(System.getProperty("os.name"))) {
            fileName = "/etc/hosts";
        } else {
            fileName = "C://WINDOWS//system32//drivers//etc//hosts";
        }
        log.info("获取hosts文件路径:" + fileName);
        return fileName;
    }

    /**
     * 判断系统是否为windows
     */
    public static boolean getIsWindows() {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return true;
        }
        return false;
    }

    /**
     * 获取系统属性
     *
     * @param propertyKey
     * @return
     */
    public static String getSystemProperty(String propertyKey) {
        for (Map.Entry entry : System.getenv().entrySet()) {
            if (entry.getKey().equals(propertyKey)) {
                return String.valueOf(entry.getValue());
            }
        }
        return "";
    }
}
