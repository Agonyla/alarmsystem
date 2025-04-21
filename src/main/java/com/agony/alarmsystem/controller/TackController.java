package com.agony.alarmsystem.controller;

import com.agony.alarmsystem.common.BaseResponse;
import com.agony.alarmsystem.common.ResultUtils;
import com.agony.alarmsystem.model.dto.TaskDTO;
import com.agony.alarmsystem.model.entity.Task;
import com.agony.alarmsystem.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Agony
 * @create: 2025/4/21 22:08
 * @describe:
 */
@RestController
@Slf4j
@RequestMapping("/task")
public class TackController {


    @Resource
    TaskService taskService;

    @PostMapping("/create")
    public BaseResponse<Task> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskService.createTask(taskDTO);
        return ResultUtils.success(task);
    }
}
