package com.helpdesk.controller;


import com.github.pagehelper.PageInfo;
import com.helpdesk.common.BaseResponseUtil;
import com.helpdesk.entity.Organization;
import com.helpdesk.entity.User;
import com.helpdesk.param.UserParam;
import com.helpdesk.service.UserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api/user")
@Api(tags = "user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping(value = "/login")
    @ApiOperation("login")
    public Object login(@RequestBody UserParam entity) {
        User login = userService.login(entity);
        String token = UUID.randomUUID().toString().replace("-", "");
        Map<String,Object> result = new HashMap<>();
        result.put("user", login);
        result.put("token", token);

        if (ObjectUtils.isEmpty(login.getIsAdmin())) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED,"Please contact the administrator to add permissions", result);
        }

        return BaseResponseUtil.constructResponseValid(BaseResponseUtil.SUCCESS, result);
    }


    @PostMapping(value = "/register")
    @ApiOperation("register")
    public Object register(@RequestBody User entity) {
        int result = userService.save(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!", result);
        }
    }


    @GetMapping("page")
    @ApiOperation("query user in the organization")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "current page", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(value = "page size", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(value = "sort field", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "sort method，in(asc、desc)", paramType = "query", dataType = "String")
    })
    public Object page(UserParam queryParam) {
        PageInfo<User> pageInfo = userService.pageQuery(queryParam);

        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", pageInfo);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("delete")
    public Object remove(@PathVariable long id) {
        int result = userService.remove(id);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!");
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!");
        }
    }



    @PutMapping
    @ApiOperation("update")
    public Object update(@RequestBody User entity) {
        int result = userService.update(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "Failed!");
        }
    }


    @GetMapping(value = "/{id}/detail")
    @ApiOperation("detail")
    public Object detail(@PathVariable long id) {
        User entity = userService.getById(id);
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", entity);
    }

    @PostMapping(value = "/logout")
    @ApiOperation("logout")
    public Object logOut() {
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Failed!");
    }

    @GetMapping("getUserByOrganization")
    public Object getUserByOrganization(Long id) {
        List<User> users = userService.selectByOrganization(id);
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "Succeed!", users);
    }
}
