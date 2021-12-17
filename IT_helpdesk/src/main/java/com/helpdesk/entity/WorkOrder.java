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
     * create time
     */
    private String createTime;


    /**
     * status：unsolved; open; closed; pending; spam; deleted
     */
    private Integer workStatus;


    /**
     * tagID
     */
    private Integer tagId;


    /**
     * userID
     */
    private Integer userId;


    /**
     * priority
     */
    private Integer priority;


    /**
     * request
     */
    @TableField(jdbcType = JdbcType.VARCHAR)
    private String questions;


    /**
     * response
     */
    private String content;

    /**
     * tag name
     */
    @TableField(exist = false)
    private String tagName;

    /**
     * username
     */
    @TableField(exist = false)
    private String userName;

    /**
     * orgID
     */
    @TableField(exist = false)
    private Long orgId;

    private Integer checkUserId;

    @TableField(exist = false)
    private String checkName;





}
