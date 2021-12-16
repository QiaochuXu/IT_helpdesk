package com.helpdesk.service;


import com.github.pagehelper.PageInfo;
import com.helpdesk.common.BaseServiceInterface;
import com.helpdesk.entity.Tags;
import com.helpdesk.param.TagsParam;

import java.util.List;


public interface TagsService extends BaseServiceInterface<Tags, TagsParam, Long> {

    PageInfo<Tags> pageQuery(TagsParam queryParam);

    List<Tags> getALl();

}
