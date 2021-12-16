package com.helpdesk.service;

import com.github.pagehelper.PageInfo;
import com.helpdesk.common.BaseServiceInterface;
import com.helpdesk.entity.Organization;
import com.helpdesk.param.OrganizationParam;

import java.util.List;


public interface OrganizationService extends BaseServiceInterface<Organization, OrganizationParam, Long> {

    PageInfo<Organization> pageQuery(OrganizationParam queryParam);

    List<Organization> getAll();

}
