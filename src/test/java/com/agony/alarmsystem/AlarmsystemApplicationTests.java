package com.agony.alarmsystem;

import com.agony.alarmsystem.channel.AlertChannel;
import com.agony.alarmsystem.model.dto.AlertRequest;
import com.agony.alarmsystem.model.dto.SubscriptionDTO;
import com.agony.alarmsystem.model.enums.AlertCode;
import com.agony.alarmsystem.model.enums.ChannelType;
import com.agony.alarmsystem.service.AlertTypeService;
import com.agony.alarmsystem.service.SubscriptionService;
import com.agony.alarmsystem.service.TaskService;
import com.agony.alarmsystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AlarmsystemApplicationTests {


    @Resource
    private SubscriptionService subscriptionService;

    @Resource
    private AlertTypeService alertTypeService;

    @Resource
    private UserService userService;

    @Resource
    private TaskService taskService;


    @Resource
    private Map<ChannelType, AlertChannel> channelMap;

    @Test
    void contextLoads() {

        System.out.println("hello world");

    }

    /**
     * 创建订阅
     */
    @Test
    void createSubscription() {
        // 准备测试数据
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setUserId(1L);
        dto.setTaskId(1L);
        dto.setAlertTypeId(2L);
        dto.setDingtalkEnabled(1);
        dto.setMessageEnabled(0);
        dto.setPhoneEnabled(0);

        // 执行测试
        boolean result = subscriptionService.createSubscription(dto);

        // 验证结果
        assertTrue(result);
    }

    /**
     * 更新订阅
     */
    @Test
    void updateSubscription_Success() {
        // 准备测试数据
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setUserId(1L);
        dto.setTaskId(1L);
        dto.setAlertTypeId(1L);
        dto.setDingtalkEnabled(1);
        dto.setMessageEnabled(1);
        dto.setPhoneEnabled(0);

        // 执行测试
        boolean result = subscriptionService.updateSubscription(dto);

        // 验证结果
        assertTrue(result);
    }


    /**
     * 发送订阅
     */
    @Test
    void sendAlertWithFAILURE() {
        // 准备测试数据
        AlertRequest alertRequest = new AlertRequest();
        alertRequest.setTaskId(1L);
        alertRequest.setAlertCode(AlertCode.FAILURE.getCode());
        alertRequest.setContent("测试告警内容");


        subscriptionService.sendAlert(alertRequest);


    }


}
