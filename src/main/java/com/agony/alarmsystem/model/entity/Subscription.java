package com.agony.alarmsystem.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订阅
 *
 * @TableName subscription
 */
@TableName(value = "subscription")
@Data
public class Subscription implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 任务ID
     */
    private Long taskId;

    /**
     * 告警类型ID
     */
    private Long alertTypeId;

    /**
     * 钉钉通知是否启用
     */
    private Integer dingtalkEnabled;

    /**
     * 短信通知是否启用
     */
    private Integer messageEnabled;

    /**
     * 电话通知是否启用
     */
    private Integer phoneEnabled;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除 1：删除；0：未删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}