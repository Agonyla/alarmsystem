package com.agony.alarmsystem.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 订阅
 *
 * @TableName subscription
 */

@Data
public class SubscriptionDTO implements Serializable {
    /**
     * id
     */
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


}