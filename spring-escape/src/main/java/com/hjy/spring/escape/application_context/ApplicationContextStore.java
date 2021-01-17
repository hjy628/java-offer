package com.hjy.spring.escape.application_context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * @auther: hjy
 * @Date: 2020/12/19 13:33
 * @Description:<h1>保存应用上下文</h1>
 */

@Slf4j
public class ApplicationContextStore {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        log.info("Coming In ApplicationContextStore!");
        ApplicationContextStore.applicationContext = applicationContext;
    }
}
