package com.helpdesk.service;


import com.github.pagehelper.PageInfo;
import com.helpdesk.common.BaseServiceInterface;
import com.helpdesk.entity.WorkOrder;
import com.helpdesk.param.WorkOrderParam;


public interface WorkOrderService extends BaseServiceInterface<WorkOrder, WorkOrderParam, Long> {

    PageInfo<WorkOrder> pageQuery(WorkOrderParam queryParam);

}
