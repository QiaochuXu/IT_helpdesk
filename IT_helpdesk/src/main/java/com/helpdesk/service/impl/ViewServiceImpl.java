package com.helpdesk.service.impl;


import com.github.pagehelper.PageInfo;
import com.helpdesk.entity.View;
import com.helpdesk.entity.ViewWork;
import com.helpdesk.entity.WorkOrder;
import com.helpdesk.mapper.ViewMapper;
import com.helpdesk.mapper.ViewWorkMapper;
import com.helpdesk.mapper.WorkOrderMapper;
import com.helpdesk.param.ViewParam;
import com.helpdesk.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ViewServiceImpl implements ViewService {


    @Autowired
    ViewMapper viewMapper;

    @Autowired
    WorkOrderMapper workOrderMapper;

    @Autowired
    ViewWorkMapper viewWorkMapper;


    @Override
    public int save(View entity) {
        int insert = viewMapper.insert(entity);
        entity.getWorkOrderIds().forEach(workOrder -> {
            ViewWork viewWork = new ViewWork();
            viewWork.setViewId(entity.getId());
            viewWork.setWorkId(workOrder);
            viewWorkMapper.insert(viewWork);
        });
        return insert;
    }

    @Override
    public int update(View entity) {
        return 0;
    }


    @Override
    public int remove(Long id) {
        return viewMapper.deleteById(id);
    }

    @Override
    public View getById(Long id) {
        return viewMapper.selectById(id);
    }

    @Override
    public List<View> pageQuery(ViewParam queryParam) {
        List<View> views = viewMapper.listQuery(queryParam);
        views.forEach(view -> {
            ViewParam viewParam = new ViewParam();
            viewParam.setId(Integer.parseInt(view.getId().toString()));
            List<Long> longs = viewMapper.listWorkIds(viewParam);
            List<WorkOrder> workOrders = new ArrayList<>();
            for (Long id : longs) {
                WorkOrder workOrder = workOrderMapper.selectById(id);
                workOrders.add(workOrder);
            }
            view.setWorkOrders(workOrders);
        });
        return views;
    }


}
