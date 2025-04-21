package com.agony.alarmsystem.service;

import com.agony.alarmsystem.model.dto.TaskDTO;
import com.agony.alarmsystem.model.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Agony
 * @description 针对表【task(任务)】的数据库操作Service
 * @createDate 2025-04-21 22:03:10
 */
public interface TaskService extends IService<Task> {


    Task createTask(TaskDTO taskDTO);


}
