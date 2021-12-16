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


    @ApiModelProperty("name")
    private String name;


    @ApiModelProperty("age")
    private Integer age;


    @ApiModelProperty("email")
    private String email;


    @ApiModelProperty("password")
    private String password;


    @ApiModelProperty("username")
    private String username;


    @ApiModelProperty("isAdmin")
    private Integer isAdmin;


    @ApiModelProperty("orgID")
    private Integer orgId;


    @ApiModelProperty("orgName")
    @TableField(exist = false)
    private String orgName;
}
