package com.mutool.framework.enums;

/**
 * 描述：菜单状态<br>
 * 作者：les<br>
 * 日期：2021/7/19 21:51<br>
 */
public enum MenuStatusEnum {
    FORBIDDEN("0", "禁用"),
    NORMAL("1", "正常");

    private String code;

    private String text;

    MenuStatusEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
