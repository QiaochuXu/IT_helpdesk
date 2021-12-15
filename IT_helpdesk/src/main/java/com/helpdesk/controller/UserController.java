package com.helpdesk.controller;


import com.github.pagehelper.PageInfo;
import com.helpdesk.common.BaseResponseUtil;
import com.helpdesk.entity.User;
import com.helpdesk.param.UserParam;
import com.helpdesk.service.UserService;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping(value = "/login")
    @ApiOperation("用户登陆")
    public Object login(@RequestBody UserParam entity) {
        User login = userService.login(entity);
        String token = UUID.randomUUID().toString().replace("-", "");
        Map<String,Object> result = new HashMap<>();
        result.put("user", login);
        result.put("token", token);

        return BaseResponseUtil.constructResponseValid(BaseResponseUtil.SUCCESS, result);
    }


    @PostMapping(value = "/register")
    @ApiOperation("用户注册")
    public Object register(@RequestBody User entity) {
        int result = userService.save(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "保存成功", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "保存失败", result);
        }
    }


    @GetMapping("page")
    @ApiOperation("查询部门下的所有员工")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Object page(UserParam queryParam) {
        PageInfo<User> pageInfo = userService.pageQuery(queryParam);

        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "查询成功", pageInfo);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("删除")
    public Object remove(@PathVariable long id) {
        int result = userService.remove(id);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "删除成功");
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "删除失败");
        }
    }



    @PutMapping(value = "/update")
    @ApiOperation("用户修改")
    public Object update(@RequestBody User entity) {
        int result = userService.update(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "修改成功", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "修改失败");
        }
    }


    @GetMapping(value = "/{id}/detail")
    @ApiOperation("详情")
    public Object detail(@PathVariable long id, @RequestHeader String token) {
        User entity = userService.getById(id);
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "查询成功", entity);
    }

    @PostMapping(value = "/logout")
    @ApiOperation("退出")
    public Object logOut() {
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "退出成功");
    }


}