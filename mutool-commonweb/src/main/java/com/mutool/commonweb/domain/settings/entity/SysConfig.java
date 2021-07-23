package com.mutool.commonweb.domain.settings.entity;

import com.mutool.commonweb.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 描述：系统参数配置<br>
 * 作者：les<br>
 * 日期：2021/7/22 09:48<br>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysConfig extends BaseEntity {

    private String name;

    private String key;

    private String value;

    private String remark;

    /**
     * 分组id
     */
    private Integer groupId;


}
