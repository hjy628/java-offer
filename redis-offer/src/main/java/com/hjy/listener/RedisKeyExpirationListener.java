package com.hjy.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @auther: hjy
 * @Date: 2021/4/18 16:02
 * @Description: 监听所有db的过期事件
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对 redis 数据失效事件，进行数据处理
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 获取到失效的 key(即订单号)，进行取消订单业务处理
        String expiredKey = message.toString();
        System.out.println(LocalDateTime.now().format(formatter)+":"+expiredKey);
    }


}
