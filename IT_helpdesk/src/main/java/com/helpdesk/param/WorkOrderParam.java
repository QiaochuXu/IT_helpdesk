package com.helpdesk.param;

import lombok.Data;


@Data
public class WorkOrderParam {
    /**
     * 分页 page size
     */
    private int ps;

    /**
     * 分页 page number
     */
    private int pn;

    /**
     * 用户查询 query
     */
    private Integer userId;

    /**
     * 问题 request
     */
    private String questions;

    /**
     * checkId
     */
    private Integer checkId;

    private Integer workStatus;


}
