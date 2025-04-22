package com.agony.alarmsystem.model.dto;

import lombok.Data;

/**
 * @author: Agony
 * @create: 2025/4/22 0:03
 * @describe:
 */
@Data
public class AlertRequest {


    /**
     * 任务ID
     */
    private Long taskId;


    private int alertCode;

    private String content;


}
