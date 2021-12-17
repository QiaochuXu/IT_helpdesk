package com.helpdesk.param;

import lombok.Data;


@Data
public class WorkOrderParam {
    /**
     * page size
     */
    private int ps;

    /**
     * page number
     */
    private int pn;

    /**
     * query
     */
    private Integer userId;

    /**
     * request
     */
    private String questions;

    /**
     * checkId
     */
    private Integer checkId;

    private Integer workStatus;


}
