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


    @ApiModelProperty("标签名称")
    private String name;


    @ApiModelProperty("标签备注")
    private String memo;


    @ApiModelProperty("1 删除 0 正常")
    private Integer del;
}
