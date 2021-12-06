package com.helpdesk.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class WorkOrder {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    private String createTime;


    /**
     * 状态：unsolved; open; closed; pending; spam; deleted
     */
    private Integer workStatus;


    /**
     * 标签id
     */
    private Integer tagId;


    /**
     * 用户id
     */
    private Integer userId;


    /**
     * 优先级
     */
    private Integer priority;


    /**
     * 问题
     */
    private String question;


    /**
     * 描述
     */
    private String content;
}
