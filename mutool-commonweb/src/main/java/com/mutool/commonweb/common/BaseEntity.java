package com.mutool.commonweb.common;

import lombok.Data;

import java.util.Date;

/**
 * 描述：<br>
 * 作者：les<br>
 * 日期：2021/4/13 23:05<br>
 */
@Data
public class BaseEntity extends IdEntity {

    private String updateBy;

    private Date updateDatetime;

    private String createBy;

    private Date createDatetime;

}
