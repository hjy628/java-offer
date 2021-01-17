package com.hjy.spring.escape.http_request_response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: hjy
 * @Date: 2021/1/8 16:26
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {


    @PostMapping("wangdan")
    public User wangdan(@RequestBody(required = false) User user){
        if (null!=user){
            return user;
        }
        return new User(-1L,"wangdan",18);
    }


}
