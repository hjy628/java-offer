package com.hjy.spring.escape;

import com.hjy.spring.escape.application_context.ApplicationContextStore;
import com.hjy.spring.escape.application_context.UseInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @auther: hjy
 * @Date: 2020/12/18 22:26
 * @Description:  <h1>SpringBoot 启动程序</h1>
 */
//@EnableScheduling
@EnableAsync
@SpringBootApplication
@ServletComponentScan("com.hjy.spring.escape")
//@ComponentScan(value = {"com.hjy.spring.escape","com.hjy.spring.outer"})
public class SpringEscapeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEscapeApplication.class,args);

        //第一种方式获取应用上下文
/*        SpringApplication application = new SpringApplication(SpringEscapeApplication.class);
        application.addInitializers(new UseInitializer());
        application.run(args);*/


        //第三种方式获取应用上下文
//        ApplicationContextStore.setApplicationContext(SpringApplication.run(SpringEscapeApplication.class,args));





    }

}
