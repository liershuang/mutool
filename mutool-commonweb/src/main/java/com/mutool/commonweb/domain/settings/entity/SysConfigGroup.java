package com.mutool.commonweb.domain.settings.entity;

import com.mutool.commonweb.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 描述：配置分组<br>
 * 作者：les<br>
 * 日期：2021/7/22 09:54<br>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysConfigGroup extends BaseEntity {

    private String key;

    private String name;

    private String remark;

}
