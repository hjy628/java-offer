package com.hjy.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;


/**
 * @auther: hjy
 * @Date: 2021/5/13 14:45
 * @Description:
 */

public class UpdateProductInfoCommand extends HystrixCommand<Boolean> {

    private Long productId;

    public UpdateProductInfoCommand(Long productId) {
        super(HystrixCommandGroupKey.Factory.asKey("UpdateProductInfoGroup"));
        this.productId = productId;
    }

    /**
     * Implement this method with code to be executed when {@link #execute()} or {@link #queue()} are invoked.
     *
     * @return R response type
     * @throws Exception if command execution fails
     */
    @Override
    protected Boolean run() throws Exception {

        //这里执行一次商品信息的更新

        //  然后清空缓存
        GetProductInfoCommand.flushCache(productId);
        return true;

    }
}
