package com.hjy.spring.escape.service;

import com.hjy.spring.escape.async_task.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

/**
 * @auther: hjy
 * @Date: 2021/1/12 15:52
 * @Description:
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AsyncTaskTest {

    @Autowired
    private AsyncService asyncService;



    @Test
    public void testAsyncProcess() throws Exception{
        asyncService.asyncProcess01();
        Future<String> future = asyncService.asyncProcess02();
        log.info("do something");
        log.info("Async Process 02 Return: {}",future.get());
    }

    @Test
    public void testAsyncException() throws Exception{
        asyncService.asyncProcess03();
        Thread.sleep(3000);
    }


}
