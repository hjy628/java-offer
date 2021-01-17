package com.hjy.spring.escape.bean_post_processor;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @auther: hjy
 * @Date: 2020/12/19 22:43
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum VideoType {

    AVI("AVI"),
    WMV("WMV"),
    MP4("MP4"),
    FLV("FLV");


    private String desc;
}
