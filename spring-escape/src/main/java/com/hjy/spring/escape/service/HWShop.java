package com.hjy.spring.escape.service;

import org.springframework.stereotype.Service;

/**
 * @auther: hjy
 * @Date: 2020/12/18 22:46
 * @Description:  <h1>Spring Bean的默认名称生成策略</h1>
 */
@Service("hWshop")
public class HWShop {

    public void print(){
        System.out.println("HwShop");
    }


}
