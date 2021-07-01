package com.hjy.advice;

import com.hjy.exceptions.CheckException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * @auther: hjy
 * @Date: 2021/6/29 11:01
 * @Description:  异常处理切面
 */
@ControllerAdvice
public class CheckAdvice {


    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String>  handleBindException(WebExchangeBindException e){
        return new ResponseEntity<String>(toStr(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CheckException.class)
    public ResponseEntity<String> handleCheckException(
            CheckException e) {
        return new ResponseEntity<String>(toStr(e), HttpStatus.BAD_REQUEST);
    }

    private String toStr(CheckException exception){
        return exception.getFieldName() + " : 错误的值" + exception.getFieldValue();
    }


    private String toStr(WebExchangeBindException exception){
        return exception.getFieldErrors()
                .stream().map(e->e.getField()+" : "+e.getDefaultMessage())
                .reduce("",(s1, s2) -> s1+"\n"+s2);
    }

}
