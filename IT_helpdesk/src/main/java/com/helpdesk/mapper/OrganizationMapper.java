package com.helpdesk.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.helpdesk.entity.Organization;
import com.helpdesk.param.OrganizationParam;
import org.springframework.stereotype.Repository;

import java.util.List;



 
@Repository
public interface OrganizationMapper extends BaseMapper<Organization> {


    List<Organization> listQuery(OrganizationParam queryParam);

    List<Organization> getAll();

}
