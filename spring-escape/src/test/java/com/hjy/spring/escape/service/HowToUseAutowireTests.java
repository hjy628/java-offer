package com.hjy.spring.escape.service;

import com.hjy.spring.escape.util.ApplicationUtils;
import com.hjy.spring.outer.Outer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther: hjy
 * @Date: 2020/12/18 23:27
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HowToUseAutowireTests {

    @Test
    public void firstTryTest(){

        assert ApplicationUtils.getApplicationContext().containsBean("hWshop");

        HowToUseAutowire useAutowire = new HowToUseAutowire();  //HowToUseAutowire没加@Component注解
        useAutowire.print();

    }


    @Test
    public void secondTryTest(){

        assert ApplicationUtils.getApplicationContext().containsBean("hWshop");

        HowToUseAutowire useAutowire = new HowToUseAutowire();  //HowToUseAutowire已加@Component注解
        useAutowire.print();
    }


    @Test
    public void thirdTryTest(){

        assert ApplicationUtils.getApplicationContext().containsBean("hWshop");

        HowToUseAutowire useAutowire = ApplicationUtils.getBean(HowToUseAutowire.class);
        useAutowire.print();

    }

    @Test
    public void fourthTryTest(){

        assert ApplicationUtils.getApplicationContext().containsBean("outer");

        ((Outer)ApplicationUtils.getBean("outer")).print();     //java.lang.AssertionError

    }

    @Test
    public void fiveTryTest(){

        assert ApplicationUtils.getApplicationContext().containsBean("outer");

        ((Outer)ApplicationUtils.getBean("outer")).print();     //已加@ComponentScan(value = {"com.hjy.spring.escape","com.hjy.spring.outer"})注解

    }

}
