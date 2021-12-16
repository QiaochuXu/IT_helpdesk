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


    @ApiModelProperty("create time")
    private String createTime;


    @ApiModelProperty("orgName")
    private String name;


    @ApiModelProperty("memo")
    private String memo;
}
