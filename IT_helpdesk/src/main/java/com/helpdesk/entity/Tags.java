package com.helpdesk.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@Api(value = "tags")
public class Tags {
    private static final long serialVersionUID = 1L;

    private Long id;


    @ApiModelProperty("tagName")
    private String name;


    @ApiModelProperty("tagDES")
    private String memo;


    @ApiModelProperty("1 deleted 0 normal")
    private Integer del;
}
