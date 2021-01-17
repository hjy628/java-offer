package com.hjy.spring.escape.bean_post_processor;

/**
 * @auther: hjy
 * @Date: 2020/12/19 22:45
 * @Description:    <h1>解码器功能接口定义</h1>
 */

public interface IDecoder {

    VideoType type();

    String decode(String data);

}
