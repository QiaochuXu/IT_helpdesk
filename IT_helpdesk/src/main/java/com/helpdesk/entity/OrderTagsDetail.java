package com.helpdesk.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Api(value = "order_tags_detail")
public class OrderTagsDetail {
    private static final long serialVersionUID = 1L;

    private Long id;


    @ApiModelProperty("标签id")
    private Integer tagId;


    @ApiModelProperty("工单id")
    private Integer workOrderId;


    @ApiModelProperty("时间")
    private String createTime;


    @ApiModelProperty("1 删除 0 正常")
    private Integer del;
}
