package com.hjy.spring.escape.service;

import com.hjy.spring.escape.util.ApplicationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther: hjy
 * @Date: 2020/12/18 22:47
 * @Description:  <h1>测试Spring Bean默认名称生成策略</h1>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HWShopTests {

    @Test
    public void testDefaultBeanName(){
        HWShop hwShop = (HWShop) ApplicationUtils.getBean("HWShop");
        HWShop hwShop_ =   ApplicationUtils.getBean(HWShop.class);
        hwShop.print();
        hwShop_.print();
    }


}
