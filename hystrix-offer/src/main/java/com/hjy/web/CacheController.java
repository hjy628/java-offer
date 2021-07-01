package com.hjy.web;

import com.hjy.commands.GetBrandNameCommand;
import com.hjy.commands.GetCityNameCommand;
import com.hjy.commands.GetProductInfoCommand;
import com.hjy.vo.ProductInfo;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: hjy
 * @Date: 2021/5/13 14:32
 * @Description:
 */
@RestController
@RequestMapping("cache")
public class CacheController {

    /**
     *
     一次性批量查询多条商品数据的请求
     *
     * @param productIds
    以,分隔的商品id列表
     * @return
    响应状态
     */
    @GetMapping("/getProductInfos")
    public String getProductInfos(String productIds) {
        int i = 0;
        for (String productId : productIds.split(",")) {
            //  对每个productId,都创建一个command
            GetProductInfoCommand getProductInfoCommand = new GetProductInfoCommand(Long.parseLong(productId));
            ProductInfo productInfo = getProductInfoCommand.execute();
            System.out.println(productId);
            System.out.println("是否是从缓存中取的结果:"
                    + getProductInfoCommand.isResponseFromCache());

            if (i==5){
                System.out.println(productId);
                GetProductInfoCommand.flushCache(Long.parseLong(productId));
            }

            i+=1;
        }
        return "success";
}


    @GetMapping("/getProductInfo")
    @ResponseBody
    public String getProductInfo(Long productId) {
        HystrixCommand<ProductInfo> getProductInfoCommand = new GetProductInfoCommand(productId);

        //通过command执行,获取最新商品数据
        ProductInfo productInfo = getProductInfoCommand.execute();

        Long brandId = productInfo.getBrandId();

        GetBrandNameCommand getBrandNameCommand = new GetBrandNameCommand(brandId);

        // 执行会抛异常报错,然后走降级
        String brandName = getBrandNameCommand.execute();
        productInfo.setBrandName(brandName);
        System.out.println(productInfo);

        return "success";

    }



}
