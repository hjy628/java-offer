package com.hjy.routers;

import com.hjy.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @auther: hjy
 * @Date: 2021/6/29 14:12
 * @Description:
 */
@Configuration
public class AllRouters {

    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler handler){
        return nest(
                //相当于类上面的requestMapping
                path("/userflux"),
                //相当于方法上的requestMapping
                //所有用户
                route(GET("/"),handler::getAllUser)
                        //创建用户
                .andRoute(POST("/").and(accept(MediaType.APPLICATION_JSON_UTF8)),handler::createUser)
                .andRoute(DELETE("/{id}"),handler::deleteUserById));
           }



}
