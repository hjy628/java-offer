package com.hjy.spring.escape.bean_post_processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @auther: hjy
 * @Date: 2020/12/19 22:46
 * @Description:
 */
@Slf4j
@Service
public class FLVDecoder implements IDecoder, InitializingBean {
    @Override
    public VideoType type() {
        return VideoType.FLV;
    }

    @Override
    public String decode(String data) {
        return this.type().getDesc()+ ": "+data;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
            log.info("Init FLVDecoder In InitializingBean");
    }
}
