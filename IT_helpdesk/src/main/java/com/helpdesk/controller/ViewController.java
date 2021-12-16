package com.helpdesk.controller;


import com.github.pagehelper.PageInfo;
import com.helpdesk.common.BaseResponseUtil;
import com.helpdesk.entity.View;
import com.helpdesk.param.ViewParam;
import com.helpdesk.service.ViewService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;



@RestController
@RequestMapping("/api/view")
@Api(tags = "View")
public class ViewController {
    @Autowired
    private ViewService viewService;

    @GetMapping("views")
    @ApiOperation("query")
    public Object page(ViewParam queryParam) {
        List<View> views = viewService.pageQuery(queryParam);

        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", views);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("delete")
    public Object remove(@PathVariable long id) {
        int result = viewService.remove(id);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!");
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!");
        }
    }


    @PostMapping(value = "/save")
    @ApiOperation("save")
    public Object save(@RequestBody View entity) {
        int result = viewService.save(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!", result);
        }
    }


    @GetMapping(value = "/{id}/detail")
    @ApiOperation("detail")
    public Object detail(@PathVariable long id) {
        View entity = viewService.getById(id);
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity);
    }


}
