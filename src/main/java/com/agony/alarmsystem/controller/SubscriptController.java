package com.agony.alarmsystem.controller;

import com.agony.alarmsystem.common.BaseResponse;
import com.agony.alarmsystem.common.ResultUtils;
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

    @PostMapping("/create")
    public BaseResponse<Boolean> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {

        boolean subscription = subscriptionService.createSubscription(subscriptionDTO);
        return ResultUtils.success(subscription);
    }

}
