package com.helpdesk.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.helpdesk.entity.OrderTagsDetail;
import com.helpdesk.param.OrderTagsDetailParam;
import org.springframework.stereotype.Repository;

import java.util.List;



 
@Repository
public interface OrderTagsDetailMapper extends BaseMapper<OrderTagsDetail> {


    List<OrderTagsDetail> listQuery(OrderTagsDetailParam queryParam);

}
