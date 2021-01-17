package com.hjy.spring.escape.bean_post_processor;

import org.springframework.stereotype.Service;

/**
 * @auther: hjy
 * @Date: 2020/12/19 22:46
 * @Description:
 */
@Service
public class WMVDecoder implements IDecoder{
    @Override
    public VideoType type() {
        return VideoType.WMV;
    }

    @Override
    public String decode(String data) {
        return this.type().getDesc()+ ": "+data;
    }
}
