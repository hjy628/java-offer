package com.h.handlers;

import com.h.beans.MethodInfo;
import com.h.beans.ServerInfo;
import com.h.interfaces.RestHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @auther: hjy
 * @Date: 2021/6/30 17:33
 * @Description:
 */

public class WebClientRestHandler implements RestHandler {

    private WebClient client;

    /**
     * 初始化服务器信息
     * 初始化WebClient
     * @param serverInfo
     */
    @Override
    public void init(ServerInfo serverInfo) {
       this.client = WebClient.create(serverInfo.getUrl());
    }

    /**
     * 调用rest请求，返回接口
     *
     * @param methodInfo
     * @return
     */
    @Override
    public Object invokeRest(MethodInfo methodInfo) {
        //返回结果
        Object result = null;


        WebClient.RequestBodySpec request = this.client
                //请求方法
                .method(methodInfo.getMethod())
                //请求url和参数
                .uri(methodInfo.getUrl(), methodInfo.getParams())
                //请求类型
                .accept(MediaType.APPLICATION_JSON);
        WebClient.ResponseSpec responseSpec = null;
        //判断是否带了body
        if (methodInfo.getBody()!=null){
          responseSpec   = request.body(methodInfo.getBody(), methodInfo.getBodyElementType())
                  //发出请求
                    .retrieve();
        }else {
            //发出请求
            responseSpec = request.retrieve();
        }

        //处理异常
        responseSpec.onStatus(httpStatus -> httpStatus.value() == 404,
                response-> Mono.just(new RuntimeException("Not Found")));

        //处理body
        if (methodInfo.isReturnFlux()){
           result = responseSpec.bodyToFlux(methodInfo.getReturnElementType());
        }else {
            result =responseSpec.bodyToMono(methodInfo.getReturnElementType());
        }

        return result;
    }
}
