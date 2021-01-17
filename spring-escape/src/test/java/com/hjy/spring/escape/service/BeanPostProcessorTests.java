package com.hjy.spring.escape.service;

import com.hjy.spring.escape.bean_post_processor.*;
import com.hjy.spring.escape.util.ApplicationUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * @auther: hjy
 * @Date: 2020/12/19 22:49
 * @Description:
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class BeanPostProcessorTests {


    @Autowired
    private WMVDecoder wmvDecoder;

    @Autowired
    private FLVDecoder flvDecoder;

    @Autowired
    private DecoderManager decoderManager;


    /**
     * <h2>获取随机的VideoType</h2>
     * @return
     */
    private VideoType getRandomVideoType(){
        return VideoType.values()[new Random().nextInt(VideoType.values().length)];
    }



    @Test
    public void testEasyUseDecoder(){

        VideoType videoType = getRandomVideoType();

        switch (videoType){
            case FLV:
                log.info(flvDecoder.decode("video"));
                break;
            case WMV:
                log.info(wmvDecoder.decode("video"));
                break;
            default:
                log.error("error!");
        }


    }

    @Test
    public void testUseDecoderManager(){
        log.info(decoderManager.decode(getRandomVideoType(),"video"));

    }

    @Test
    public void testCheckBeanFactoryPostProcessor(){
        ThirdPartyClass class01 = ApplicationUtils.getBean(ThirdPartyClass.class);
        ThirdPartyClass class02 = ApplicationUtils.getBean(ThirdPartyClass.class);

        System.out.println(class01.hashCode());
        System.out.println(class02.hashCode());


    }



}
