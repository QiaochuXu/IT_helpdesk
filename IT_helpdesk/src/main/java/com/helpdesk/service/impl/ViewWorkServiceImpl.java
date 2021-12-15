package com.helpdesk.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.helpdesk.entity.ViewWork;
import com.helpdesk.mapper.ViewWorkMapper;
import com.helpdesk.param.ViewWorkParam;
import com.helpdesk.service.ViewWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ViewWorkServiceImpl implements ViewWorkService {


    @Autowired
    ViewWorkMapper viewWorkMapper;

    @Override
    public int save(ViewWork entity) {
        return viewWorkMapper.insert(entity);
    }

    @Override
    public int update(ViewWork entity) {
        return viewWorkMapper.updateById(entity);
    }

    @Override
    public int remove(Long id) {
        return viewWorkMapper.deleteById(id);
    }

    @Override
    public ViewWork getById(Long id) {
        return viewWorkMapper.selectById(id);
    }



}
