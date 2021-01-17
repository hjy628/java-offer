package com.hjy.spring.escape.http_status_code;

import com.hjy.spring.escape.transaction_lost.CustomeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther: hjy
 * @Date: 2021/1/7 17:34
 * @Description: <h1>全局统一异常处理</h1>
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = CustomeException.class)
    public ResponseEntity<GeneralResponse<String>> handleCustomException(HttpServletRequest request,CustomeException ex){
        GeneralResponse<String> result = new GeneralResponse<>(0,"");
        result.setData(ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }


}
