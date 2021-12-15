package com.helpdesk.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@Api(value = "user")
public class User {
    private static final long serialVersionUID = 1L;

    private Long id;


    @ApiModelProperty("用户名")
    private String name;


    @ApiModelProperty("年龄")
    private Integer age;


    @ApiModelProperty("邮箱")
    private String email;


    @ApiModelProperty("密码")
    private String password;


    @ApiModelProperty("用户登陆名")
    private String username;


    @ApiModelProperty("是否为管理员")
    private Integer isAdmin;


    @ApiModelProperty("组织id")
    private Integer orgId;


    @ApiModelProperty("组织name")
    @TableField(exist = false)
    private String orgName;
}
