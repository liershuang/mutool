package com.mutool.framework.domain.sys.entity;

import lombok.Data;

import java.util.List;

/**
 * 描述：<br>
 * 作者：les<br>
 * 日期：2021/7/19 22:19<br>
 */
@Data
public class MenuTree {

    /**
     * 主键 ID
     */
    private Integer id;

    /**
     * 父 ID
     */
    private Integer pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 菜单类型 0：目录 1、菜单 2、按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态 -1、禁用 0、正常
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    private List<MenuTree> childList;

}
