package com.hjy.web;

import com.hjy.commands.GetCityNameCommand;
import com.hjy.commands.GetProductInfoCommand;
import com.hjy.commands.GetProductInfosCommand;
import com.hjy.vo.ProductInfo;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rx.Observer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther: hjy
 * @Date: 2021/5/13 11:02
 * @Description:
 */
@RestController
public class ProductCacheController {


    @GetMapping("/getProductInfo")
    @ResponseBody
    public String getProductInfo(Long productId) {
        HystrixCommand<ProductInfo> getProductInfoCommand = new GetProductInfoCommand(productId);

        //通过command执行,获取最新商品数据
        ProductInfo productInfo = getProductInfoCommand.execute();

        Long cityId = productInfo.getCityId();

//        GetCityNameCommand getCityNameCommand = new GetCityNameCommand(cityId);

        // 获取本地内存(cityName)的代码会被信号量进行资源隔离
//        String cityName = getCityNameCommand.execute();
//        productInfo.setCityName(cityName);
        System.out.println(productInfo);

        return "success";

    }


    /**
     * @param productIds 英文逗号分隔
     */
    @GetMapping("/getProducts")
    public String getProduct(String productIds) {
        GetProductInfosCommand getProductsCommand = new GetProductInfosCommand(productIds.split(","));
        // 第一种获取数据模式
        getProductsCommand.observe().subscribe(new Observer<ProductInfo>() {

            @Override
            public void onCompleted() {
                System.out.println("Observer: onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Observer: onError:" + e);
            }

            @Override
            public void onNext(ProductInfo productInfo) {
                System.out.println("Observer: onNext:" + productInfo);
            }
        });
        System.out.println("方法已执行完成");
        return "success";

    }





}
