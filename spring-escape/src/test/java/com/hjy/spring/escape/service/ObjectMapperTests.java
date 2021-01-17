package com.hjy.spring.escape.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjy.spring.escape.use_jackson.Coupon;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

/**
 * @auther: hjy
 * @Date: 2021/1/12 16:48
 * @Description:
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ObjectMapperTests {

    @Autowired
    private ObjectMapper mapper;


    @Test
    public void testUseJacksonAnnotation()throws Exception{
        /*
        Coupon coupon = Coupon.fake();

//        coupon.setTemplate(null);
        log.info("ObjectMapper Se Coupon: {}",mapper.writeValueAsString(coupon));
*/

        String jsonCoupon = "{\"id\":\"1\",\"userId\":\"100\",\"couponCode\":\"123456\",\"assignTime\":\"2021-01-12 04:58:59\",\"status\":\"USABLE\"}";

        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


        log.info("{}",mapper.readValue(jsonCoupon,Coupon.class).getId());
        log.info("{}",mapper.readValue(jsonCoupon,Coupon.class).toString());


    }


}
