package com.hjy.spring.escape.scheduled_task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @auther: hjy
 * @Date: 2021/1/12 15:30
 * @Description:
 */
@Slf4j
@Component

public class ScheduledTask {


    @Scheduled(fixedRate = 1000)
    public void task01() throws Exception{
        log.info("Scheduled Task process task01.");
        while (true){
            Thread.sleep(2000);
            log.info("Scheduled Task process something!");
        }
    }


    @Scheduled(fixedRate = 1000)
    public void task02() throws Exception{
        log.info("Scheduled Task process task02.");
    }



}
