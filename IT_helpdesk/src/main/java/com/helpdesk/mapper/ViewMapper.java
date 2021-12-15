package com.helpdesk.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.helpdesk.entity.View;
import com.helpdesk.param.ViewParam;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface ViewMapper extends BaseMapper<View> {


    List<View> listQuery(ViewParam queryParam);

    List<Long> listWorkIds(ViewParam queryParam);
}
