package com.h.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @auther: hjy
 * @Date: 2021/6/30 14:54
 * @Description:  方法调用信息类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MethodInfo {

    /**
     * 请求url
     */
    private String url;


    /**
     * 请求方法
     */
    private HttpMethod method;

    /**
     * 请求参数(url)
     */
    private Map<String, Object> params;

    /**
     * 请求body
     */
    private Mono body;


    /**
     * 请求body的类型
     */
    private Class<?> bodyElementType;


    /**
     * 返回flux或mono
     */
    private boolean returnFlux;

    /**
     * 返回对象的类型
     */
    private Class<?> returnElementType;



}
