package com.helpdesk.service;


import com.github.pagehelper.PageInfo;
import com.helpdesk.common.BaseServiceInterface;
import com.helpdesk.entity.OrderTagsDetail;
import com.helpdesk.param.OrderTagsDetailParam;


public interface OrderTagsDetailService extends BaseServiceInterface<OrderTagsDetail, OrderTagsDetailParam, Long> {

    PageInfo<OrderTagsDetail> pageQuery(OrderTagsDetailParam queryParam);

}
