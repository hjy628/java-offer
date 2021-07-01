package com.h.proxys;

import com.h.ApiServer;
import com.h.beans.MethodInfo;
import com.h.beans.ServerInfo;
import com.h.handlers.WebClientRestHandler;
import com.h.interfaces.ProxyCreator;
import com.h.interfaces.RestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @auther: hjy
 * @Date: 2021/6/30 14:49
 * @Description: 使用JDK动态代理实现代理类
 */
@Slf4j
public class JDKProxyCreator implements ProxyCreator {
    /**
     * 创建代理类
     *
     * @param type
     * @return
     */
    @Override
    public Object createProxy(Class<?> type) {
        log.info("createProxy:"+type);

        //根据接口得到API服务器信息
        ServerInfo serverInfo = extractServerInfo(type);
        log.info("serverInfo:"+serverInfo);

        /**
         *  给每一个代理类一个实现
         */
        RestHandler handler = new WebClientRestHandler();

        /**
         * 初始化服务器信息(初始化webclient)
         */
        handler.init(serverInfo);



        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{type}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /**
                 * 根据方法和参数得到调用信息
                 */
                MethodInfo methodInfo = extractMethodInfo(method,args);
                log.info("methodInfo:"+methodInfo);

                /**
                 * 调用rest
                 */
               return handler.invokeRest(methodInfo);
            }

            /**
             * 根据方法定义和调用参数得到调用的相关信息
             * @param method
             * @param args
             * @return
             */
            private MethodInfo extractMethodInfo(Method method, Object[] args) {
                MethodInfo methodInfo = new MethodInfo();

                extractUrlAndMethod(method, methodInfo);

                extractRequestParamAndBody(method, args, methodInfo);

                //提取返回对象信息
                extractReturnInfo(method, methodInfo);

                return methodInfo;
            }

            /**
             * 提取返回对象信息
             * @param method
             * @param methodInfo
             */
            private void extractReturnInfo(Method method, MethodInfo methodInfo) {
                /**
                 * 返回flux或是mono
                 * isAssignableFrom判断类型是否某个的子类
                 * instanceof判断实例是否某个的子类
                 */
                boolean isFlux = method.getReturnType().isAssignableFrom(Flux.class);
                methodInfo.setReturnFlux(isFlux);

                /**
                 * 得到返回对象的实际类型
                 */
                Class<?> elementType = extractElementType(method.getGenericReturnType());
                methodInfo.setReturnElementType(elementType);

            }

            /**
             * 得到范型类型的实际类型
             * @param genericReturnType
             * @return
             */
            private Class<?> extractElementType(Type genericReturnType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
                return (Class<?>)actualTypeArguments[0];
            }

            /**
             * 得到调用的参数和body
             * @param method
             * @param args
             * @param methodInfo
             */
            private void extractRequestParamAndBody(Method method, Object[] args, MethodInfo methodInfo) {
                Parameter[] parameters = method.getParameters();

                //参数和值对应的map
                Map<String, Object> params = new LinkedHashMap<>();
                methodInfo.setParams(params);

                for (int i = 0; i < parameters.length; i++) {

                    //是否带@PathVariable
                    PathVariable annoPath = parameters[i].getAnnotation(PathVariable.class);
                    if (annoPath != null){
                        params.put(annoPath.value(),args[i]);
                    }

                    //是否带RequestBody
                    RequestBody annoBody = parameters[i].getAnnotation(RequestBody.class);

                    if (annoBody!=null){
                        methodInfo.setBody((Mono<?>)args[i]);
                        methodInfo.setBodyElementType(extractElementType(parameters[i].getParameterizedType()));
                    }
                }
            }
        });
    }

    /**
     * 得到请求url和请求方法
     * @param method
     * @param methodInfo
     */
    private void extractUrlAndMethod(Method method, MethodInfo methodInfo) {
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation :
                annotations) {
            /**
             * GET
             */
            if (annotation instanceof GetMapping){
                GetMapping a = (GetMapping) annotation;
                methodInfo.setUrl(a.value()[0]);
                methodInfo.setMethod(HttpMethod.GET);
            }
            /**
             * POST
             */
            if (annotation instanceof PostMapping){
                PostMapping a = (PostMapping) annotation;
                methodInfo.setUrl(a.value()[0]);
                methodInfo.setMethod(HttpMethod.POST);
            }
            /**
             * PUT
             */
            if (annotation instanceof PutMapping){
                PutMapping a = (PutMapping) annotation;
                methodInfo.setUrl(a.value()[0]);
                methodInfo.setMethod(HttpMethod.PUT);
            }
            /**
             * DELETE
             */
            if (annotation instanceof DeleteMapping){
                DeleteMapping a = (DeleteMapping) annotation;
                methodInfo.setUrl(a.value()[0]);
                methodInfo.setMethod(HttpMethod.DELETE);
            }
        }
    }

    /**
     * 提取服务器信息
     * @param type
     * @return
     */
    private ServerInfo extractServerInfo(Class<?> type) {
        ServerInfo serverInfo = new ServerInfo();

        ApiServer anno = type.getAnnotation(ApiServer.class);
        serverInfo.setUrl(anno.value());

        return serverInfo;
    }
}
