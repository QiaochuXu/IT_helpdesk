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




@RestController
@RequestMapping("/api/work")
@Api(tags = "work order")
public class WorkOrderController {

    @Autowired
    private WorkOrderService workOrderService;

    @GetMapping("page")
    @ApiOperation("query")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "current page", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(value = "page size", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(value = "sort field", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "sort method，in(asc、desc)", paramType = "query", dataType = "String")
    })
    public Object page(WorkOrderParam queryParam) {
        // paging query
        PageInfo<WorkOrder> pageInfo = workOrderService.pageQuery(queryParam);

        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", pageInfo);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("delete")
    public Object remove(@PathVariable long id) {
        int result = workOrderService.remove(id);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!");
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!");
        }
    }


    @PostMapping
    @ApiOperation("add")
    public Object save(@RequestBody WorkOrder entity) {
        int result = workOrderService.save(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!", result);
        }
    }

    @PutMapping
    @ApiOperation("update")
    public Object update(@RequestBody WorkOrder entity) {
        int result = workOrderService.update(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!");
        }
    }


    @GetMapping(value = "/{id}/detail")
    @ApiOperation("detail")
    public Object detail(@PathVariable long id) {
        WorkOrder entity = workOrderService.getById(id);
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity);
    }


}
