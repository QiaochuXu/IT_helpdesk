package com.helpdesk.service;


import com.github.pagehelper.PageInfo;
import com.helpdesk.common.BaseServiceInterface;
import com.helpdesk.entity.Tags;
import com.helpdesk.param.TagsParam;


public interface TagsService extends BaseServiceInterface<Tags, TagsParam, Long> {

    PageInfo<Tags> pageQuery(TagsParam queryParam);

}
