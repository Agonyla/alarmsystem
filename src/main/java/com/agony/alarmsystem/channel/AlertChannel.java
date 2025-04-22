package com.agony.alarmsystem.channel;

import com.agony.alarmsystem.model.entity.Task;
import com.agony.alarmsystem.model.entity.User;
import com.agony.alarmsystem.model.enums.AlertCode;

/**
 * @author: Agony
 * @create: 2025/4/22 7:37
 * @describe:
 */
public interface AlertChannel {

    void send(Task task, AlertCode alertCode, User user, String content);
}
