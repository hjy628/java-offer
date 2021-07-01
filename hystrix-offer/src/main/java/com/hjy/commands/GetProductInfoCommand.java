package com.hjy.commands;

import com.alibaba.fastjson.JSONObject;
import com.hjy.vo.ProductInfo;
import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;

import java.util.concurrent.TimeUnit;


/**
 * @auther: hjy
 * @Date: 2021/5/13 10:53
 * @Description:
 */

public class GetProductInfoCommand extends HystrixCommand<ProductInfo> {


    private Long productId;

    private static final HystrixCommandKey KEY = HystrixCommandKey.Factory.asKey("GetProductInfoCommand");


    public GetProductInfoCommand( Long productId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetProductInfoCommandGroup"))
        .andCommandKey(KEY)
                //线程池相关配置信息
       .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
       //设置线程池大小为8
                       .withCoreSize(8)
        //设置等待队列大小为10
       .withMaxQueueSize(10)
       .withQueueSizeRejectionThreshold(12)
       )
        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
        //是否允许断路器工作
                .withCircuitBreakerEnabled(true)
        //滑动窗口中，最少有多少个请求，才可能触发断路
                .withCircuitBreakerRequestVolumeThreshold(20)
        //异常比例达到多少，才触发断路，默认50%
                .withCircuitBreakerErrorThresholdPercentage(40)
        //断路后多少时间内直接reject请求，之后进入half-open状态
                .withCircuitBreakerSleepWindowInMilliseconds(3000)
                .withExecutionTimeoutInMilliseconds(20000)
                .withFallbackIsolationSemaphoreMaxConcurrentRequests(25)
        ));
        this.productId = productId;
    }

    /**
     * Implement this method with code to be executed when {@link #execute()} or {@link #queue()} are invoked.
     *
     * @return R response type
     * @throws Exception if command execution fails
     */
    @Override
    protected ProductInfo run() throws Exception {

        System.out.println(" 调用接口查询商品数据,productId="     + productId);


        if (productId == -1L) {
            throw new Exception();
        }

        // 请求过来,会在这里hang住3秒钟
        if (productId == -2L) {
            TimeUnit.SECONDS.sleep(3);
        }


        String url =  "http://localhost:8081/getProductInfo?productId=" + productId;
        //调用商品服务
//        String response = new RestttpClient().get(url);
        String response ="{productId:1,productName:'hello',cityId:'1',cityName:'',brandId:'2',brandName:''}";
        return JSONObject.parseObject(response,ProductInfo.class);
    }

    @Override
    protected ProductInfo getFallback() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductName("降级商品");
        return productInfo;
    }

    /**
     *
     * 每次请求的结果,都会放在Hystrix绑定的请求上下文上
     * Key to be used for request caching.
     * <p>
     * By default this returns null which means "do not cache".
     * <p>
     * To enable caching override this method and return a string key uniquely representing the state of a command instance.
     * <p>
     * If multiple command instances in the same request scope match keys then only the first will be executed and all others returned from cache.
     *
     * @return cacheKey
     */
    @Override
    protected String getCacheKey() {
        return "product_info_" + productId;
    }


    /**
     *
     将某个商品id的缓存清空
     *
     * @param productId
    商品id
     */
    public static void flushCache(Long productId) {
        HystrixRequestCache.getInstance(KEY,
                HystrixConcurrencyStrategyDefault.getInstance()).clear("product_info_"+productId);
    }



}
