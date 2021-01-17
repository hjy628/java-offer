package com.hjy.spring.escape.service;

import com.hjy.spring.escape.default_singleton.DefaultHwsopManagerService;
import com.hjy.spring.escape.util.ApplicationUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther: hjy
 * @Date: 2020/12/19 15:22
 * @Description:
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DefaultHwsopManagerServiceTests {

    @Test
    public void testDefaultSingleton(){

        DefaultHwsopManagerService service01 = ApplicationUtils.getBean(DefaultHwsopManagerService.class);

        DefaultHwsopManagerService service02 = ApplicationUtils.getBean(DefaultHwsopManagerService.class);

        service01.addMchs("WD");
        service01.addMchs("HJY");
        log.info("Service01 has mchs: {}",service01.getMchs());


        service02.addMchs("TY");
        log.info("Service01 has mchs: {}",service01.getMchs());


    }


    @Test
    public void testDefaultSingleton_(){

        DefaultHwsopManagerService service01 = ApplicationUtils.getBean(DefaultHwsopManagerService.class);

        DefaultHwsopManagerService service02 = ApplicationUtils.getBean(DefaultHwsopManagerService.class);

        assert  service01.hashCode() == service02.hashCode();

    }


}
