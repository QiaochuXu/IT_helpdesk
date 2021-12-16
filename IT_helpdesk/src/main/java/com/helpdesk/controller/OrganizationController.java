package com.helpdesk.controller;

import com.github.pagehelper.PageInfo;
import com.helpdesk.common.BaseResponseUtil;
import com.helpdesk.entity.Organization;
import com.helpdesk.param.OrganizationParam;
import com.helpdesk.service.OrganizationService;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
@RequestMapping("/api/organization")
@Api(tags = "organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("page")
    @ApiOperation("query")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "current page", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(value = "page size", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(value = "sort field", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "sort method，in(asc、desc)", paramType = "query", dataType = "String")
    })
    public Object page(OrganizationParam queryParam) {
        PageInfo<Organization> pageInfo = organizationService.pageQuery(queryParam);

        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", pageInfo);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("delete")
    public Object remove(@PathVariable long id) {
        int result = organizationService.remove(id);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!");
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!");
        }
    }


    @PostMapping(value = "/save")
    @ApiOperation("save")
    public Object save(@RequestBody Organization entity) {
        int result = organizationService.save(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!", result);
        }
    }

    @PutMapping(value = "/update")
    @ApiOperation("update")
    public Object update(@RequestBody Organization entity) {
        int result = organizationService.update(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!");
        }
    }


    @GetMapping(value = "/{id}/detail")
    @ApiOperation("detail")
    public Object detail(@PathVariable long id) {
        Organization entity = organizationService.getById(id);
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity);
    }

    @GetMapping("organizationList")
    public Object getList() {
        List<Organization> all = organizationService.getAll();
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", all);
    }


}
