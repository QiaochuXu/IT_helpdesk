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
     * workorderID
     */
    @ApiModelProperty("work_order_id")
    private Long workId;


    /**
     * viewID
     */
    @ApiModelProperty("view_id")
    private Long viewId;
}
