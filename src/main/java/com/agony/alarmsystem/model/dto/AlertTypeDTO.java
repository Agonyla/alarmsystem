package com.agony.alarmsystem.model.dto;

import lombok.Data;

/**
 * @author: Agony
 * @create: 2025/4/21 22:33
 * @describe:
 */
@Data
public class AlertTypeDTO {

    private Long id;

    /**
     * 告警类型名称
     */
    private String name;

    /**
     * 告警类型编码
     */

    private int code;
}
