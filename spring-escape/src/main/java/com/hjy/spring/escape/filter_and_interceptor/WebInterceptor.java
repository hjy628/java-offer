package com.hjy.spring.escape.filter_and_interceptor;

import com.hjy.spring.escape.http_request_response.UserIdInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * @auther: hjy
 * @Date: 2021/1/8 16:01
 * @Description:
 */
@Configuration
@Component
public class WebInterceptor implements WebMvcConfigurer {
    /**
     * Add Spring MVC lifecycle interceptors for pre- and post-processing of
     * controller method invocations. Interceptors can be registered to apply
     * to all requests or be limited to a subset of URL patterns.
     * <p><strong>Note</strong> that interceptors registered here only apply to
     * controllers and not to resource handler requests. To intercept requests for
     * static resources either declare a
     * {@link MappedInterceptor MappedInterceptor}
     * bean or switch to advanced configuration mode by extending
     * {@link WebMvcConfigurationSupport
     * WebMvcConfigurationSupport} and then override {@code resourceHandlerMapping}.
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**").order(0);
            registry.addInterceptor(new UpdateLogInterceptor()).addPathPatterns("/**").order(1);
            registry.addInterceptor(new UserIdInterceptor()).addPathPatterns("/**").order(2);
    }
}
