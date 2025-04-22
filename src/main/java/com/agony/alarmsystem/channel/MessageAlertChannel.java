package com.agony.alarmsystem.channel;

import com.agony.alarmsystem.model.entity.Task;
import com.agony.alarmsystem.model.entity.User;
import com.agony.alarmsystem.model.enums.AlertCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: Agony
 * @create: 2025/4/22 7:41
 * @describe:
 */
@Slf4j
@Component
public class MessageAlertChannel implements AlertChannel {


    @Override
    public void send(Task task, AlertCode alertCode, User user, String content) {


        // TODO: 发送短信消息

        log.info("{} 发生 {} 告警，向用户 {} 的短信 {} 发送告警: {}", task.getType(), alertCode.getName(), user.getUsername(), user.getMessage(), content);

    }
}
