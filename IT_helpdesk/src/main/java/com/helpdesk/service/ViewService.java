package com.helpdesk.service;


import com.github.pagehelper.PageInfo;
import com.helpdesk.common.BaseServiceInterface;
import com.helpdesk.entity.View;
import com.helpdesk.param.ViewParam;

import java.util.List;

public interface ViewService extends BaseServiceInterface<View, ViewParam, Long> {

     List<View> pageQuery(ViewParam queryParam);


}
