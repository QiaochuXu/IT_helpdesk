package com.helpdesk.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.helpdesk.entity.Tags;
import com.helpdesk.param.TagsParam;
import org.springframework.stereotype.Repository;

import java.util.List;



 
@Repository
public interface TagsMapper extends BaseMapper<Tags> {


    List<Tags> listQuery(TagsParam queryParam);

    List<Tags> getALl();

}
