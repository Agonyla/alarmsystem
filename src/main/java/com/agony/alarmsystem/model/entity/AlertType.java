package com.agony.alarmsystem.model.entity;

import com.agony.alarmsystem.model.enums.AlertCode;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 告警类型
 *
 * @TableName alert_type
 */
@TableName(value = "alert_type")
@Data
public class AlertType implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 告警类型名称
     */
    private String name;

    /**
     * 告警类型编码
     */

    private AlertCode code;

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