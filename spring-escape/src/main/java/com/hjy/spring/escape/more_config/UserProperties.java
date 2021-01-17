package com.hjy.spring.escape.more_config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @auther: hjy
 * @Date: 2021/1/12 14:20
 * @Description:
 */
@Data
@ToString
@Component
@ConfigurationProperties(prefix = "escape.user")
public class UserProperties {

    private String name;
    private Integer age;


}
