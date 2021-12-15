package com.helpdesk.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@EqualsAndHashCode(callSuper = false)
@Api(description = "view_work")
public class ViewWork {
    private static final long serialVersionUID = 1L;

    private Long id;


    /**
     * 工单id
     */
    @ApiModelProperty("工单id")
    private Long workId;


    /**
     * 视图id
     */
    @ApiModelProperty("视图id")
    private Long viewId;
}
