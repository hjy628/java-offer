package com.hjy.spring.escape.http_status_code;

import lombok.Data;

/**
 * @auther: hjy
 * @Date: 2021/1/7 17:21
 * @Description: <h1>通用响应对象</h1>
 */
@Data
public class GeneralResponse<T> {

    private Integer code;
    private String message;
    private T data;

    public GeneralResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
