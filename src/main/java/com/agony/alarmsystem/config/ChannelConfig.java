package com.agony.alarmsystem.config;

import com.agony.alarmsystem.channel.AlertChannel;
import com.agony.alarmsystem.channel.DingTalkAlertChannel;
import com.agony.alarmsystem.channel.MessageAlertChannel;
import com.agony.alarmsystem.channel.PhoneAlertChannel;
import com.agony.alarmsystem.model.enums.ChannelType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Agony
 * @create: 2025/4/22 7:46
 * @describe:
 */
@Configuration
public class ChannelConfig {


    @Bean
    public Map<ChannelType, AlertChannel> channelMap(DingTalkAlertChannel dingTalkAlertChannel, MessageAlertChannel smsAlertChannel, PhoneAlertChannel phoneAlertChannel) {

        Map<ChannelType, AlertChannel> channelMap = new HashMap<>();
        channelMap.put(ChannelType.DINGTALK, dingTalkAlertChannel);
        channelMap.put(ChannelType.MESSAGE, smsAlertChannel);
        channelMap.put(ChannelType.PHONE, phoneAlertChannel);
        return channelMap;
    }
}
