package com.agony.alarmsystem.controller;

import com.agony.alarmsystem.common.BaseResponse;
import com.agony.alarmsystem.common.ResultUtils;
import com.agony.alarmsystem.model.dto.AlertTypeDTO;
import com.agony.alarmsystem.model.entity.AlertType;
import com.agony.alarmsystem.service.AlertTypeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Agony
 * @create: 2025/4/21 23:03
 * @describe:
 */

@RestController
@RequestMapping("/alert-types")
public class AlertTypeController {


    @Resource
    AlertTypeService alertTypeService;

    @PostMapping("/create")
    public BaseResponse<AlertType> createAlertType(@RequestBody AlertTypeDTO alertType) {
        AlertType savedAlertType = alertTypeService.createAlertType(alertType);
        return ResultUtils.success(savedAlertType);
    }
}
