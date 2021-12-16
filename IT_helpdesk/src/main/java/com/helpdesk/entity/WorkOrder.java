package com.helpdesk.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;


@Data
@TableName(value = "work_order")
public class WorkOrder {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间 create time
     */
    private String createTime;


    /**
     * 状态 status：unsolved; open; closed; pending; spam; deleted
     */
    private Integer workStatus;


    /**
     * 标签id tagID
     */
    private Integer tagId;


    /**
     * 用户id userID
     */
    private Integer userId;


    /**
     * 优先级 priority
     */
    private Integer priority;


    /**
     * 问题 request
     */
    @TableField(jdbcType = JdbcType.VARCHAR)
    private String questions;


    /**
     * 描述 response
     */
    private String content;

    /**
     * 标签名称 tag name
     */
    @TableField(exist = false)
    private String tagName;

    /**
     * 用户名称 username
     */
    @TableField(exist = false)
    private String userName;

    /**
     * 部门id orgID
     */
    @TableField(exist = false)
    private Long orgId;

    private Integer checkUserId;

    @TableField(exist = false)
    private String checkName;





}
