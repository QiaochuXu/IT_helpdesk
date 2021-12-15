package com.helpdesk.service;


import com.github.pagehelper.PageInfo;
import com.helpdesk.common.BaseServiceInterface;
import com.helpdesk.entity.User;
import com.helpdesk.param.UserParam;


public interface UserService extends BaseServiceInterface<User, UserParam, Long> {

     PageInfo<User> pageQuery(UserParam queryParam);

     User login(UserParam userParam);

}
