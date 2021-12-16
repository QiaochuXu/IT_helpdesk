package com.helpdesk.service;


import com.github.pagehelper.PageInfo;
import com.helpdesk.common.BaseServiceInterface;
import com.helpdesk.entity.User;
import com.helpdesk.param.UserParam;

import java.util.List;


public interface UserService extends BaseServiceInterface<User, UserParam, Long> {

     PageInfo<User> pageQuery(UserParam queryParam);

     User login(UserParam userParam);

    List<User> selectByOrganization(Long id);
}
