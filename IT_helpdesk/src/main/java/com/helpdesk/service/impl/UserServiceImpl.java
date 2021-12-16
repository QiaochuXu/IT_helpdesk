package com.helpdesk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.helpdesk.common.BaseResponseException;
import com.helpdesk.common.ResponseErrorCode;
import com.helpdesk.entity.User;
import com.helpdesk.mapper.UserMapper;
import com.helpdesk.param.UserParam;
import com.helpdesk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;


import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public int save(User entity) {

        // 判断用户名称是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", entity.getUsername());
        User user = userMapper.selectOne(wrapper);
        if (!ObjectUtils.isEmpty(user)) {
            throw new BaseResponseException(ResponseErrorCode.USER_NAME_CODE_EXCEPTION);
        }

        return userMapper.insert(entity);
    }

    @Override
    public int update(User entity) {
        if (entity.getId() == null || entity.getId() == 0L) {
            log.error("UserServiceImpl update error {}", "id不能为空");
            throw new BaseResponseException(ResponseErrorCode.USER_DETAIL_EXCEPTION);
        }

        return userMapper.updateById(entity);
    }

    @Override
    public int remove(Long id) {
        if (id == null || id == 0L) {
            log.error("UserServiceImpl delete error {}", "id不能为空");
            throw new BaseResponseException(ResponseErrorCode.USER_DETAIL_EXCEPTION);
        }
        return userMapper.deleteById(id);
    }

    @Override
    public User getById(Long id) {
        if (id == null || id == 0L) {
            log.error("UserServiceImpl detail error {}", "id不能为空");
            throw new BaseResponseException(ResponseErrorCode.USER_DETAIL_EXCEPTION);
        }

        return userMapper.selectById(id);
    }

    @Override
    public PageInfo<User> pageQuery(UserParam queryParam) {
        return PageHelper.startPage(queryParam.getPn(), queryParam.getPs())
                .doSelectPageInfo(() -> userMapper.listQuery(queryParam));
    }

    @Override
    public User login(UserParam userParam) {
        // 根据用户名查询
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userParam.getUsername());
        User user = userMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(user)) {
            throw new BaseResponseException(ResponseErrorCode.USER_USER_NAME_EXCEPTION);
        }

        // 判断密码
        if (!user.getPassword().equals(userParam.getPassword())) {
            throw new BaseResponseException(ResponseErrorCode.USER_PASSWORD_EXCEPTION);
        }
        return user;
    }

    @Override
    public List<User> selectByOrganization(Long id) {
        return userMapper.selectByOrganization(id);
    }


}
