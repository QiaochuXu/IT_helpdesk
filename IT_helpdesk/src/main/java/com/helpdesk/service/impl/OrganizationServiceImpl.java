package com.helpdesk.service.impl;

import com.helpdesk.entity.Organization;
import com.helpdesk.mapper.OrganizationMapper;
import com.helpdesk.param.OrganizationParam;
import com.helpdesk.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.springframework.stereotype.Service;


import java.util.Map;


@Service
public class OrganizationServiceImpl implements OrganizationService {


    @Autowired
    OrganizationMapper organizationMapper;

    @Override
    public int save(Organization entity) {
        return organizationMapper.insert(entity);
    }

    @Override
    public int update(Organization entity) {
        if (entity.getId() == null || entity.getId() == 0L) {
            // 自己定义的异常方法
        }

        return organizationMapper.updateById(entity);
    }

    @Override
    public int remove(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
        }
        return organizationMapper.deleteById(id);
    }

    @Override
    public Organization getById(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
        }

        return organizationMapper.selectById(id);
    }

    @Override
    public PageInfo<Organization> pageQuery(OrganizationParam queryParam) {
        return PageHelper.startPage(queryParam.getPn(), queryParam.getPs())
                .doSelectPageInfo(() -> organizationMapper.listQuery(queryParam));
    }


}
