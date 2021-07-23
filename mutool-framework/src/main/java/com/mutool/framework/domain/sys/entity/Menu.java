package com.mutool.framework.domain.sys.entity;

import com.mutool.commonweb.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author les
 * @since 2021-04-20
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {

    private static final long serialVersionUID = -1817220052315935677L;

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


}
