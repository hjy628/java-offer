package com.hjy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @auther: hjy
 * @Date: 2021/4/18 16:00
 * @Description:
 */
@RestController
@RequestMapping("/")
@SpringBootApplication
public class RedisApplication {

    @Autowired
    private RedisTemplate redisTemplate;



    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class,args);
    }



    @GetMapping("/setKey")
    public String setRedisKey(String key){

        redisTemplate.opsForValue().set(key, LocalDateTime.now(),30, TimeUnit.SECONDS);
        return "ok:"+key+"-----"+LocalDateTime.now().plusSeconds(30).format(formatter);
    }


}
