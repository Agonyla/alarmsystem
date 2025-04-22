package com.agony.alarmsystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.agony.alarmsystem.channel.AlertChannel;
import com.agony.alarmsystem.exception.ErrorCode;
import com.agony.alarmsystem.exception.ThrowUtils;
import com.agony.alarmsystem.mapper.SubscriptionMapper;
import com.agony.alarmsystem.model.dto.AlertRequest;
import com.agony.alarmsystem.model.dto.SubscriptionDTO;
import com.agony.alarmsystem.model.entity.AlertType;
import com.agony.alarmsystem.model.entity.Subscription;
import com.agony.alarmsystem.model.entity.Task;
import com.agony.alarmsystem.model.entity.User;
import com.agony.alarmsystem.model.enums.ChannelType;
import com.agony.alarmsystem.service.AlertTypeService;
import com.agony.alarmsystem.service.SubscriptionService;
import com.agony.alarmsystem.service.TaskService;
import com.agony.alarmsystem.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Agony
 * @description 针对表【subscription(订阅)】的数据库操作Service实现
 * @createDate 2025-04-21 23:22:10
 */
@Service
public class SubscriptionServiceImpl extends ServiceImpl<SubscriptionMapper, Subscription>
        implements SubscriptionService {


    @Resource
    AlertTypeService alertTypeService;

    @Resource
    UserService userService;

    @Resource
    TaskService taskService;


    @Resource
    Map<ChannelType, AlertChannel> channelMap;

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

        boolean result = this.save(subscription);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR, "订阅失败");
        return true;
    }

    @Override
    public boolean updateSubscription(SubscriptionDTO subscriptionDTO) {
        ThrowUtils.throwIf(subscriptionDTO == null, ErrorCode.PARAMS_ERROR, "订阅信息不能为空");
        Long userId = subscriptionDTO.getUserId();
        ThrowUtils.throwIf(userId == null || userId <= 0, ErrorCode.PARAMS_ERROR, "用户ID不能为空");
        Long taskId = subscriptionDTO.getTaskId();
        ThrowUtils.throwIf(taskId == null || taskId <= 0, ErrorCode.PARAMS_ERROR, "任务ID不能为空");
        Long alertTypeId = subscriptionDTO.getAlertTypeId();
        ThrowUtils.throwIf(alertTypeId == null || alertTypeId <= 0, ErrorCode.PARAMS_ERROR, "告警类型ID不能为空");

        Subscription subscription = new Subscription();
        BeanUtil.copyProperties(subscriptionDTO, subscription);


        LambdaQueryWrapper<Subscription> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Subscription::getUserId, userId)
                .eq(Subscription::getTaskId, taskId)
                .eq(Subscription::getAlertTypeId, alertTypeId);

        boolean result = this.update(subscription, queryWrapper);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR, "更新订阅失败");
        return true;

    }


    @Override
    public void sendAlert(AlertRequest alertRequest) {


        Long taskId = alertRequest.getTaskId();
        Task task = taskService.getById(taskId);
        int code = alertRequest.getAlertCode();
        String content = alertRequest.getContent();
        AlertType alertType = alertTypeService.getByCode(code);
        Long alertTypeId = alertType.getId();


        List<Subscription> subscriptionList = this.lambdaQuery().eq(Subscription::getTaskId, taskId)
                .eq(Subscription::getAlertTypeId, alertTypeId).list();


        for (Subscription subscription : subscriptionList) {

            Long userId = subscription.getUserId();
            User user = userService.getById(userId);
            // 发送钉钉消息
            if (subscription.getDingtalkEnabled() == 1 && user.getDingtalkId() != null) {
                channelMap.get(ChannelType.DINGTALK).send(task, alertType.getCode(), user, content);
            }

            // 发送短信
            if (subscription.getMessageEnabled() == 1 && user.getMessage() != null) {
                channelMap.get(ChannelType.MESSAGE).send(task, alertType.getCode(), user, content);
            }

            // 发送电话
            if (subscription.getPhoneEnabled() == 1 && user.getPhone() != null) {
                channelMap.get(ChannelType.PHONE).send(task, alertType.getCode(), user, content);
            }
        }
    }
}




