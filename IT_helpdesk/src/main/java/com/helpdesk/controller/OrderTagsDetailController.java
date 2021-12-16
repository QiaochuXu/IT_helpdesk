package com.helpdesk.controller;


import com.github.pagehelper.PageInfo;
import com.helpdesk.common.BaseResponseUtil;
import com.helpdesk.entity.OrderTagsDetail;
import com.helpdesk.param.OrderTagsDetailParam;
import com.helpdesk.service.OrderTagsDetailService;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;



@RestController
@RequestMapping("/api/orderTagsDetail")
@Api(tags = "connection between tag and work order")
public class OrderTagsDetailController {
    @Autowired
    private OrderTagsDetailService orderTagsDetailService;

    @GetMapping("page")
    @ApiOperation("query")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "current page", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(value = "page size", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(value = "sort field", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "sort method，in(asc、desc)", paramType = "query", dataType = "String")
    })
    public Object page(OrderTagsDetailParam queryParam) {
        PageInfo pageInfo = orderTagsDetailService.pageQuery(queryParam);

        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", pageInfo);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("delete")
    public Object remove(@PathVariable long id) {
        int result = orderTagsDetailService.remove(id);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!");
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!");
        }
    }


    @PostMapping(value = "/save")
    @ApiOperation("add")
    public Object save(@RequestBody OrderTagsDetail entity) {
        int result = orderTagsDetailService.save(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!", result);
        }
    }

    @PutMapping(value = "/update")
    @ApiOperation("update")
    public Object update(@RequestBody OrderTagsDetail entity) {
        int result = orderTagsDetailService.update(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!");
        }
    }


    @GetMapping(value = "/{id}/detail")
    @ApiOperation("detail")
    public Object detail(@PathVariable long id) {
        OrderTagsDetail entity = orderTagsDetailService.getById(id);
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity);
    }


}
