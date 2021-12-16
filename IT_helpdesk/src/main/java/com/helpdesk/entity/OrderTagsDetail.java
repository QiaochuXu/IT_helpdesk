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


    @ApiModelProperty("tagId")
    private Integer tagId;


    @ApiModelProperty("workorder_id")
    private Integer workOrderId;


    @ApiModelProperty("createtime")
    private String createTime;


    @ApiModelProperty("1 deleted 0 normal")
    private Integer del;
}
