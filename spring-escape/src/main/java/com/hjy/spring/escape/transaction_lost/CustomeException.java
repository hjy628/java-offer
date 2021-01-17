package com.hjy.spring.escape.transaction_lost;

/**
 * @auther: hjy
 * @Date: 2020/12/27 21:56
 * @Description:
 */

public class CustomeException extends Exception {

    public CustomeException(String message) {
        super(message);
    }
}
