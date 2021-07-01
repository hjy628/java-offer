package com.hjy.commands;

import com.alibaba.fastjson.JSON;
import com.hjy.caches.LocationCache;
import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

/**
 * @auther: hjy
 * @Date: 2021/5/13 13:53
 * @Description:
 */

public class GetCityNameCommand extends HystrixCommand<String> {

    private Long cityId;

    private static final Setter cachedSetter = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetCityNameCommandGroup"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("GetCityNameCommandGroup"))
                    .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GetCityNameCommandGroup"));


    public GetCityNameCommand( Long cityId) {
        //设置信号量隔离策略
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetCityNameCommandGroup"))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
            //设置4秒钟超时，看是否有效果
            .withExecutionTimeoutInMilliseconds(6000)
            .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
            //信号量最大请求数量限制
             .withExecutionIsolationSemaphoreMaxConcurrentRequests(2))
        );
        this.cityId = cityId;
    }


    /**
     * Implement this method with code to be executed when {@link #execute()} or {@link #queue()} are invoked.
     *
     * @return R response type
     * @throws Exception if command execution fails
     */
    @Override
    protected String run() throws Exception {
        //需要进行信号量隔离的代码

        System.out.println(Thread.currentThread().getName());
//        System.out.println("睡眠 5 秒，模拟");
//        TimeUnit.SECONDS.sleep(5);

        return  LocationCache.getCityName(cityId);

    }
}
