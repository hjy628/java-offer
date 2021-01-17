package com.hjy.spring.escape.scheduled_task;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @auther: hjy
 * @Date: 2021/1/12 15:38
 * @Description:
 */
@Configuration
public class ScheduleConfig {

    @Bean
    public TaskScheduler taskScheduler(){


        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(5);
        return taskScheduler;
    }


}
