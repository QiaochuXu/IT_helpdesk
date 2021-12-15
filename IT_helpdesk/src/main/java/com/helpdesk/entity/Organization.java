package com.helpdesk.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class Organization {
    private static final long serialVersionUID = 1L;

    private Long id;


    @ApiModelProperty("创建时间")
    private String createTime;


    @ApiModelProperty("部门名")
    private String name;


    @ApiModelProperty("备注")
    private String memo;
}
