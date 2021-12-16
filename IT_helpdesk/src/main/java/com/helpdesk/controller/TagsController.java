package com.helpdesk.controller;

import com.github.pagehelper.PageInfo;
import com.helpdesk.common.BaseResponseUtil;
import com.helpdesk.entity.Tags;
import com.helpdesk.param.TagsParam;
import com.helpdesk.service.TagsService;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
@RequestMapping("/api/tags")
@Api(tags = "标签")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @GetMapping("page")
    @ApiOperation("标签分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Object page(TagsParam queryParam) {
        PageInfo<Tags> pageInfo = tagsService.pageQuery(queryParam);

        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "查询成功", pageInfo);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("标签删除")
    public Object remove(@PathVariable long id) {
        int result = tagsService.remove(id);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "删除成功");
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "删除失败");
        }
    }


    @PostMapping
    @ApiOperation("标签创建")
    public Object save(@RequestBody Tags entity) {
        int result = tagsService.save(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "保存成功", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "保存失败", result);
        }
    }

    @PutMapping
    @ApiOperation("标签修改")
    public Object update(@RequestBody Tags entity) {
        int result = tagsService.update(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "修改成功", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "修改失败");
        }
    }


    @GetMapping(value = "/{id}/detail")
    @ApiOperation("标签详情")
    public Object detail(@PathVariable long id) {
        Tags entity = tagsService.getById(id);
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "查询成功", entity);
    }

    @GetMapping("getTagsList")
    public Object getTagsList() {
        List<Tags> list =  tagsService.getALl();
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "查询成功", list);
    }


}
