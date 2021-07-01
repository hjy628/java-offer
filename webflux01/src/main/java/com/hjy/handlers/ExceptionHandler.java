package com.hjy.handlers;

import com.hjy.exceptions.CheckException;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * @auther: hjy
 * @Date: 2021/6/29 15:19
 * @Description:
 */
@Component
@Order(-2)
public class ExceptionHandler implements WebExceptionHandler {
    /**
     * Handle the given exception. A completion signal through the return value
     * indicates error handling is complete while an error signal indicates the
     * exception is still not handled.
     *
     * @param exchange the current exchange
     * @param ex       the exception to handle
     * @return {@code Mono<Void>} to indicate when exception handling is complete
     */
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        //设置响应头400
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        //设置返回类型
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);

        //异常信息
        String errMsg =toStr(ex);

        DataBuffer dataBuffer = response.bufferFactory().wrap(errMsg.getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }


    private String toStr(Throwable exception){
        //已知异常
        if (exception instanceof CheckException){
            CheckException e = (CheckException)exception;
            return e.getFieldName() +" : invalid value"+e.getFieldValue();
        }
        //未知异常,需要打印堆栈，方便定位
            else {
                exception.printStackTrace();
                return exception.toString();
        }
    }


}
