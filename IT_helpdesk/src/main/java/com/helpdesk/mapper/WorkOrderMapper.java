package com.helpdesk.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.helpdesk.entity.WorkOrder;
import com.helpdesk.param.WorkOrderParam;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WorkOrderMapper extends BaseMapper<WorkOrder> {


    List<WorkOrder> listQuery(WorkOrderParam queryParam);

}
