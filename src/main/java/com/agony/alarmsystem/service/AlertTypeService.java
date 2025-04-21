package com.agony.alarmsystem.service;

import com.agony.alarmsystem.model.dto.AlertTypeDTO;
import com.agony.alarmsystem.model.entity.AlertType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Agony
 * @description 针对表【alert_type(告警类型)】的数据库操作Service
 * @createDate 2025-04-21 22:17:15
 */
public interface AlertTypeService extends IService<AlertType> {


    AlertType createAlertType(AlertTypeDTO alertTypeDTO);
}
