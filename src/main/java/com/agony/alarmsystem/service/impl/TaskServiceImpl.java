package com.agony.alarmsystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.agony.alarmsystem.exception.ErrorCode;
import com.agony.alarmsystem.exception.ThrowUtils;
import com.agony.alarmsystem.mapper.TaskMapper;
import com.agony.alarmsystem.model.dto.TaskDTO;
import com.agony.alarmsystem.model.entity.Task;
import com.agony.alarmsystem.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Agony
 * @description 针对表【task(任务)】的数据库操作Service实现
 * @createDate 2025-04-21 22:03:10
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
        implements TaskService {


    @Override
    public Task createTask(TaskDTO taskDTO) {

        ThrowUtils.throwIf(taskDTO == null, ErrorCode.PARAMS_ERROR, "任务参数不能为空");
        
        Task task = new Task();
        BeanUtil.copyProperties(taskDTO, task);


        boolean result = this.save(task);
        return task;
    }


}




