package com.hjy.spring.escape.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @auther: hjy
 * @Date: 2020/12/18 23:25
 * @Description: <h1>学会使用 @AutoWired 注解</h1>
 */
@Component
public class HowToUseAutowire {

    @Autowired
    private HWShop shop;

    public void print(){
        shop.print();
    }

}
