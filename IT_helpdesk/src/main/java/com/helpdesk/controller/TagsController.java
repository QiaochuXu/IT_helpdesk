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
@Api(tags = "tag")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @GetMapping("page")
    @ApiOperation("query")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "current page", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(value = "page size", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(value = "sort field", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "sort method，in(asc、desc)", paramType = "query", dataType = "String")
    })
    public Object page(TagsParam queryParam) {
        PageInfo<Tags> pageInfo = tagsService.pageQuery(queryParam);

        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", pageInfo);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("delete")
    public Object remove(@PathVariable long id) {
        int result = tagsService.remove(id);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!");
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!");
        }
    }


    @PostMapping
    @ApiOperation("add")
    public Object save(@RequestBody Tags entity) {
        int result = tagsService.save(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!", result);
        }
    }

    @PutMapping
    @ApiOperation("update")
    public Object update(@RequestBody Tags entity) {
        int result = tagsService.update(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!");
        }
    }


    @GetMapping(value = "/{id}/detail")
    @ApiOperation("detail")
    public Object detail(@PathVariable long id) {
        Tags entity = tagsService.getById(id);
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity);
    }

    @GetMapping("getTagsList")
    public Object getTagsList() {
        List<Tags> list =  tagsService.getALl();
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", list);
    }


}
