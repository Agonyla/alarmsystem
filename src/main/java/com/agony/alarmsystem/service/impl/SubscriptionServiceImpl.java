package com.agony.alarmsystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.agony.alarmsystem.exception.ErrorCode;
import com.agony.alarmsystem.exception.ThrowUtils;
import com.agony.alarmsystem.mapper.SubscriptionMapper;
import com.agony.alarmsystem.model.dto.SubscriptionDTO;
import com.agony.alarmsystem.model.entity.Subscription;
import com.agony.alarmsystem.service.SubscriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Agony
 * @description 针对表【subscription(订阅)】的数据库操作Service实现
 * @createDate 2025-04-21 23:22:10
 */
@Service
public class SubscriptionServiceImpl extends ServiceImpl<SubscriptionMapper, Subscription>
        implements SubscriptionService {

    @Override
    public boolean createSubscription(SubscriptionDTO subscriptionDTO) {

        ThrowUtils.throwIf(subscriptionDTO == null, ErrorCode.PARAMS_ERROR, "订阅信息不能为空");
        Long userId = subscriptionDTO.getUserId();
        ThrowUtils.throwIf(userId == null || userId <= 0, ErrorCode.PARAMS_ERROR, "用户ID不能为空");
        Long taskId = subscriptionDTO.getTaskId();
        ThrowUtils.throwIf(taskId == null || taskId <= 0, ErrorCode.PARAMS_ERROR, "任务ID不能为空");
        Long alertTypeId = subscriptionDTO.getAlertTypeId();
        ThrowUtils.throwIf(alertTypeId == null || alertTypeId <= 0, ErrorCode.PARAMS_ERROR, "告警类型ID不能为空");

        Subscription subscription = new Subscription();
        BeanUtil.copyProperties(subscriptionDTO, subscription);

        return this.save(subscription);
    }
}




