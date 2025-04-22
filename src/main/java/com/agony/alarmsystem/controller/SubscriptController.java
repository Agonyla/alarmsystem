package com.agony.alarmsystem.controller;

import com.agony.alarmsystem.common.BaseResponse;
import com.agony.alarmsystem.common.ResultUtils;
import com.agony.alarmsystem.exception.ErrorCode;
import com.agony.alarmsystem.exception.ThrowUtils;
import com.agony.alarmsystem.model.dto.AlertRequest;
import com.agony.alarmsystem.model.dto.SubscriptionDTO;
import com.agony.alarmsystem.service.SubscriptionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Agony
 * @create: 2025/4/21 23:31
 * @describe:
 */

@RestController
@RequestMapping("/subscription")
public class SubscriptController {


    @Resource
    SubscriptionService subscriptionService;

    /**
     * 创建订阅
     *
     * @param subscriptionDTO
     * @return
     */
    @PostMapping("/create")
    public BaseResponse<Boolean> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {

        boolean result = subscriptionService.createSubscription(subscriptionDTO);
        return ResultUtils.success(result);
    }

    /**
     * 更新订阅
     *
     * @param subscriptionDTO
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {

        boolean result = subscriptionService.updateSubscription(subscriptionDTO);
        return ResultUtils.success(result);
    }

    /**
     * 发送告警
     *
     * @param alertRequest
     * @return
     */
    @PostMapping("/sendAlert")
    public BaseResponse<String> sendAlert(@RequestBody AlertRequest alertRequest) {

        ThrowUtils.throwIf(alertRequest == null, ErrorCode.PARAMS_ERROR, "alertRequest is null");
        subscriptionService.sendAlert(alertRequest);
        return ResultUtils.success("告警发送成功");
    }


}
