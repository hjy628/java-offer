package com.hjy.spring.escape.http_status_code;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @auther: hjy
 * @Date: 2021/1/7 17:28
 * @Description:
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "This is Second Way To Set Http Status Code!")
public class BadRequestException extends RuntimeException{
}
