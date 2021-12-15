package com.helpdesk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.helpdesk.common.BaseResponseException;
import com.helpdesk.common.ResponseErrorCode;
import com.helpdesk.entity.Tags;
import com.helpdesk.entity.User;
import com.helpdesk.mapper.TagsMapper;
import com.helpdesk.param.TagsParam;
import com.helpdesk.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import java.util.Map;


@Service
public class TagsServiceImpl implements TagsService {


    @Autowired
    TagsMapper tagsMapper;

    @Override
    public int save(Tags entity) {
        QueryWrapper<Tags> wrapper = new QueryWrapper<>();
        wrapper.eq("username", entity.getName());
        Tags tags = tagsMapper.selectOne(wrapper);
        if (!ObjectUtils.isEmpty(tags)) {
            throw new BaseResponseException(ResponseErrorCode.TAG_NAME_EXCEPTION);
        }

        return tagsMapper.insert(entity);
    }

    @Override
    public int update(Tags entity) {
        if (entity.getId() == null || entity.getId() == 0L) {
            // 自己定义的异常方法
        }

        return tagsMapper.updateById(entity);
    }

    @Override
    public int remove(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
        }
        return tagsMapper.deleteById(id);
    }

    @Override
    public Tags getById(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
        }

        return tagsMapper.selectById(id);
    }

    @Override
    public PageInfo<Tags> pageQuery(TagsParam queryParam) {
        return PageHelper.startPage(queryParam.getPn(), queryParam.getPs())
                .doSelectPageInfo(() -> tagsMapper.listQuery(queryParam));
    }


}
