package com.helpdesk.service.impl;

import com.helpdesk.entity.OrderTagsDetail;
import com.helpdesk.mapper.OrderTagsDetailMapper;
import com.helpdesk.param.OrderTagsDetailParam;
import com.helpdesk.service.OrderTagsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.springframework.stereotype.Service;


import java.util.Map;


@Service
public class OrderTagsDetailServiceImpl implements OrderTagsDetailService {


    @Autowired
    OrderTagsDetailMapper orderTagsDetailMapper;

    @Override
    public int save(OrderTagsDetail entity) {
        return orderTagsDetailMapper.insert(entity);
    }

    @Override
    public int update(OrderTagsDetail entity) {
        if (entity.getId() == null || entity.getId() == 0L) {
            // 自己定义的异常方法
        }

        return orderTagsDetailMapper.updateById(entity);
    }

    @Override
    public int remove(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
        }
        return orderTagsDetailMapper.deleteById(id);
    }

    @Override
    public OrderTagsDetail getById(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
        }

        return orderTagsDetailMapper.selectById(id);
    }

    @Override
    public PageInfo<OrderTagsDetail> pageQuery(OrderTagsDetailParam queryParam) {
        return PageHelper.startPage(queryParam.getPn(), queryParam.getPs())
                .doSelectPageInfo(() -> orderTagsDetailMapper.listQuery(queryParam));
    }


}
