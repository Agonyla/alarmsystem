package com.agony.alarmsystem.model.dto;

import com.agony.alarmsystem.model.enums.TaskType;
import lombok.Data;

/**
 * @author: Agony
 * @create: 2025/4/21 22:04
 * @describe:
 */
@Data
public class TaskDTO {

    private Long id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务类型
     */
    private TaskType type;
}
