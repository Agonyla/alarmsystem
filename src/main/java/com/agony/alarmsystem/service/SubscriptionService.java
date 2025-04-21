package com.agony.alarmsystem.service;

import com.agony.alarmsystem.model.dto.SubscriptionDTO;
import com.agony.alarmsystem.model.entity.Subscription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Agony
 * @description 针对表【subscription(订阅)】的数据库操作Service
 * @createDate 2025-04-21 23:22:10
 */
public interface SubscriptionService extends IService<Subscription> {


    /**
     * 创建订阅（根据名称和编码）
     */
    boolean createSubscription(SubscriptionDTO subscriptionDTO);
}
