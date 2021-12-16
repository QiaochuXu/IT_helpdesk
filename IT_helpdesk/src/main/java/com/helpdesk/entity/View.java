package com.helpdesk.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;



@Data
@EqualsAndHashCode(callSuper = false)
@Api(description = "view")
public class View {
    private static final long serialVersionUID = 1L;

    private Long id;


    /**
     *视图名称 view name
     */
    @ApiModelProperty("view_name")
    private String name;


    /**
     *视图备注 view description
     */
    @ApiModelProperty("view_des")
    private String memo;


    /**
     *用户id user id
     */
    @ApiModelProperty("user_id")
    private Integer userId;

    /**
     *用户可查看 who can view
     */
    @ApiModelProperty("user_id")
    private Integer viewUserId;

    @ApiModelProperty("user_name")
    @TableField(exist = false)
    private String userName;

    @ApiModelProperty("check_name")
    @TableField(exist = false)
    private String userViewName;


    @TableField(exist = false)
    private List<WorkOrder> workOrders;


    @TableField(exist = false)
    private List<Long> workOrderIds;
}
