package com.helpdesk.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.helpdesk.entity.User;
import com.helpdesk.param.UserParam;
import org.springframework.stereotype.Repository;

import java.util.List;




@Repository
public interface UserMapper extends BaseMapper<User> {


    List<User> listQuery(UserParam queryParam);

    List<User> selectByOrganization(Long orgId);
}
