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
    @ApiOperation("查询视图")
    public Object page(ViewParam queryParam) {
        List<View> views = viewService.pageQuery(queryParam);

        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "查询成功", views);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("删除视图")
    public Object remove(@PathVariable long id) {
        int result = viewService.remove(id);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "删除成功");
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "删除失败");
        }
    }


    @PostMapping(value = "/save")
    @ApiOperation("保存视图")
    public Object save(@RequestBody View entity) {
        int result = viewService.save(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "保存成功", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "保存失败", result);
        }
    }


    @GetMapping(value = "/{id}/detail")
    @ApiOperation("详情")
    public Object detail(@PathVariable long id) {
        View entity = viewService.getById(id);
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "查询成功", entity);
    }


}
