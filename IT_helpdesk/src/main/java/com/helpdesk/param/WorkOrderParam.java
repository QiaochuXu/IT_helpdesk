package com.helpdesk.param;

import lombok.Data;


@Data
public class WorkOrderParam {
    /**
     * 分页
     */
    private int ps;

    /**
     * 分页
     */
    private int pn;

    /**
     * 用户查询
     */
    private Integer userId;

    /**
     * 问题
     */
    private String questions;

    /**
     * checkId
     */
    private Integer checkId;


}
