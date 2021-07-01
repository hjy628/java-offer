package com.hjy;

import com.hjy.filters.HystrixRequestContextFilter;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: hjy
 * @Date: 2021/5/13 10:35
 * @Description:
 */

@RestController
@EnableHystrix
@SpringBootApplication
public class HystrixOfferApplication {



    public static void main(String[] args) {
        SpringApplication.run(HystrixOfferApplication.class, args);
    }

    //通过HystrixCommand注解，手动指定一个降级方法，出现异常后会调用该降级方法
    @RequestMapping("/getName")
    @HystrixCommand(fallbackMethod = "getNameFallback")
    public String getName(String username) throws Exception{
        if(username.equals("hjy")){
            return "this is hjy";
        }else{
            throw new Exception();
        }
    }

    /**
     * 出错后会调用该降级方法，返回指定的信息
     * @param username
     * @return
     */
    public String getNameFallback(String username){
        return " this username is not exist ";
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new HystrixRequestContextFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }


}
