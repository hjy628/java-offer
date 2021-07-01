package com.hjy.commands;

import com.alibaba.fastjson.JSON;
import com.hjy.vo.ProductInfo;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @auther: hjy
 * @Date: 2021/5/13 11:04
 * @Description:
 */

public class GetProductInfosCommand extends HystrixObservableCommand<ProductInfo> {


    private String[] productIds;


    public GetProductInfosCommand( String[] productIds) {
        //还是绑定在同一线程池
        super(HystrixCommandGroupKey.Factory.asKey("GetProductInfoCommandGroup"));
        this.productIds = productIds;
    }

    /**
     * Implement this method with code to be executed when {@link #observe()} or {@link #toObservable()} are invoked.
     *
     * @return R response type
     */
    @Override
    protected Observable<ProductInfo> construct() {


        return Observable.unsafeCreate((Observable.OnSubscribe<ProductInfo>) onSubscribe -> {
//            for (Long pid : pids) {
//                String url = "http://localhost:7000/getProduct?productId=" + pid;
//                String response = HttpClientUtils.sendGetRequest(url);
//                onSubscribe.onNext(JSON.parseObject(response, ProductInfo.class));
//            }
//            onSubscribe.onCompleted();
                    try {
                        if (!onSubscribe.isUnsubscribed()) {
                            for (String pid : productIds) {
                                String url = "http://localhost:7000/getProduct?productId=" + pid;
//                                String response = HttpClientUtils.sendGetRequest(url);
                                String response = "{productId:1,productName:'hello',cityName:'常州'}";
                                TimeUnit.SECONDS.sleep(5);
                                onSubscribe.onNext(JSON.parseObject(response, ProductInfo.class));
                            }
                            onSubscribe.onCompleted();
                        }
                    } catch (Exception e) {
                        onSubscribe.onError(e);
                    }
                }


        ).subscribeOn(Schedulers.io());
    }
}
