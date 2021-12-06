package com.helpdesk.controller;


import com.github.pagehelper.PageInfo;
import com.helpdesk.common.BaseResponseUtil;
import com.helpdesk.entity.WorkOrder;
import com.helpdesk.param.WorkOrderParam;
import com.helpdesk.service.WorkOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;



@RestController
@RequestMapping("/api/work")
@Api(tags = "工单")
public class WorkOrderController {
    @Autowired
    private WorkOrderService workOrderService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Object page(WorkOrderParam queryParam) {
        PageInfo<WorkOrder> pageInfo = workOrderService.pageQuery(queryParam);

        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "查询成功", pageInfo);
    }
	
}
