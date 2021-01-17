package com.hjy.spring.escape.multi_usable_bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @auther: hjy
 * @Date: 2020/12/19 16:04
 * @Description:  <h1>配置多个Redis数据源</h1>
 */
@Configuration
public class RedisConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    @Autowired
    public RedisConfig(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }


    @Primary
    @Bean(name = "hwShopRedisTemplate")
    public RedisTemplate<String, Object> getHwShopRedisTemplate(RedisConnectionFactory factory){
            RedisTemplate<String, Object> template = new RedisTemplate<>();

            RedisSerializer<String> stringSerializer = new StringRedisSerializer();

            template.setConnectionFactory(factory);
            template.setKeySerializer(stringSerializer);
            template.setValueSerializer(stringSerializer);

            return template;
    }

    @Bean(name = "wdRedisTemplate")
    public RedisTemplate<String, Object> getWdRedisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        JdkSerializationRedisSerializer redisSerializer  = new JdkSerializationRedisSerializer();
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();

        template.setConnectionFactory(factory);
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(redisSerializer);

        return template;
    }





}
