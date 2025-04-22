package com.agony.alarmsystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.agony.alarmsystem.exception.ErrorCode;
import com.agony.alarmsystem.exception.ThrowUtils;
import com.agony.alarmsystem.mapper.AlertTypeMapper;
import com.agony.alarmsystem.model.dto.AlertTypeDTO;
import com.agony.alarmsystem.model.entity.AlertType;
import com.agony.alarmsystem.model.enums.AlertCode;
import com.agony.alarmsystem.service.AlertTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Agony
 * @description 针对表【alert_type(告警类型)】的数据库操作Service实现
 * @createDate 2025-04-21 22:17:15
 */
@Service
public class AlertTypeServiceImpl extends ServiceImpl<AlertTypeMapper, AlertType>
        implements AlertTypeService {

    @Resource
    AlertTypeMapper alertTypeMapper;

    @Override
    public AlertType createAlertType(AlertTypeDTO alertTypeDTO) {


        ThrowUtils.throwIf(alertTypeDTO == null, ErrorCode.PARAMS_ERROR, "参数不能为空");

        int code = alertTypeDTO.getCode();
        AlertCode enumByCode = AlertCode.getEnumByCode(code);
        AlertType alertType = new AlertType();
        BeanUtil.copyProperties(alertTypeDTO, alertType);
        alertType.setCode(enumByCode);

        boolean result = this.save(alertType);
        return alertType;
    }

    @Override
    public AlertType getByCode(int code) {
        AlertCode alertCode = AlertCode.getEnumByCode(code);
        return this.lambdaQuery().eq(AlertType::getCode, alertCode).one();
    }


}




