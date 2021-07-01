package com.hjy.commands;

import com.hjy.caches.BrandCache;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @auther: hjy
 * @Date: 2021/5/13 15:08
 * @Description:   获取品牌名称的Command
 */

public class GetBrandNameCommand extends HystrixCommand<String> {

    private Long brandId;

    public GetBrandNameCommand(Long brandId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BrandNameCommandGroup"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("GetBrandNameCommand"))
                                        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
//    设置降级机制最大并发请求数
 .withFallbackIsolationSemaphoreMaxConcurrentRequests(10)));
        this.brandId = brandId;
    }

    /**
     * Implement this method with code to be executed when {@link #execute()} or {@link #queue()} are invoked.
     *
     * @return R response type
     * @throws Exception if command execution fails
     */
    @Override
    protected String run() throws Exception {
        //     这里正常的逻辑应该是去调用一个品牌服务的接口获取名称
        //     如果调用失败,报错了,那么就会去调用fallback降级机制

        //这里我们直接模拟调用报错,抛出异常
        throw new Exception();
}

    /**
     * If {@link #execute()} or {@link #queue()} fails in any way then this method will be invoked to provide an opportunity to return a fallback response.
     * <p>
     * This should do work that does not require network transport to produce.
     * <p>
     * In other words, this should be a static or cached result that can immediately be returned upon failure.
     * <p>
     * If network traffic is wanted for fallback (such as going to MemCache) then the fallback implementation should invoke another {@link HystrixCommand} instance that protects against that network
     * access and possibly has another level of fallback that does not involve network access.
     * <p>
     * DEFAULT BEHAVIOR: It throws UnsupportedOperationException.
     *
     * @return R or throw UnsupportedOperationException if not implemented
     */
    @Override
    protected String getFallback() {
        return BrandCache.getBrandName(brandId);
    }
}
