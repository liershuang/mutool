package com.mutool.mail.enums;

/**
 * 描述：模板类型<br>
 * 作者：les<br>
 * 日期：2021/3/20 14:11<br>
 */
public enum TemplateTypeEnum {
    FREEMARKER("freemarker"),
    THYMELEAF("thymeleaf")
    ;

    private String code;

    TemplateTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
