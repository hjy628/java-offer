package com.hjy.spring.escape.use_jackson;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @auther: hjy
 * @Date: 2021/1/12 16:40
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum  CouponStatus {

    USABLE("可用的",1),
    USED("使用过的",2);

    private String desc;
    private Integer code;



}
