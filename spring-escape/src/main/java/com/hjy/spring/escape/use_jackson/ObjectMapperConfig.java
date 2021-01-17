package com.hjy.spring.escape.use_jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @auther: hjy
 * @Date: 2021/1/12 16:31
 * @Description:
 */
@Configuration
public class ObjectMapperConfig {


    @Bean
    @Primary
    public ObjectMapper objectMapper(){
        
        ObjectMapper mapper = new ObjectMapper();

        //忽略json字符串中不识别的字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return mapper;
    }


}
