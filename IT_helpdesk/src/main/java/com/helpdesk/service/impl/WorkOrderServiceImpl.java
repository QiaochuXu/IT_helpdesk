package com.helpdesk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.helpdesk.entity.WorkOrder;
import com.helpdesk.mapper.WorkOrderMapper;
import com.helpdesk.param.WorkOrderParam;
import com.helpdesk.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WorkOrderServiceImpl implements WorkOrderService {


    @Autowired
    WorkOrderMapper workOrderMapper;

    @Override
    public int save(WorkOrder entity) {
        return workOrderMapper.insert(entity);
    }

    @Override
    public int update(WorkOrder entity) {
        if (entity.getId() == null || entity.getId() == 0L) {
            // 自己定义的异常方法
        }

        return workOrderMapper.updateById(entity);
    }

    @Override
    public int remove(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
        }
        return workOrderMapper.deleteById(id);
    }

    @Override
    public WorkOrder getById(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
        }
        return workOrderMapper.selectById(id);
    }

    @Override
    public PageInfo<WorkOrder> pageQuery(WorkOrderParam queryParam) {
        // pn 页数 1 ps 值 10
        return PageHelper.startPage(queryParam.getPn(), queryParam.getPs())
                .doSelectPageInfo(() -> workOrderMapper.listQuery(queryParam));
    }


}
