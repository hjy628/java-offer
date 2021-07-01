package com.hjy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @auther: hjy
 * @Date: 2021/6/30 10:28
 * @Description:
 */
@RestController
public class TestController {


    //直接注入定义的接口
    @Autowired
    IUserApi userApi;

    @GetMapping("/")
    public void test(){
//        Flux<User> users = userApi.getAllUsers();
//        users.subscribe(System.out::println);

        String id = "1";
        userApi.getUserById(id).subscribe(user ->
        {
            System.out.println("getUserById:"+user);
        },e->{
            System.err.println("找不到用户:"+e.getMessage());
        });

        userApi.deleteUserById(id).subscribe();


        Flux<User> users = userApi.getAllUsers();
        users.subscribe(System.out::println);

    }


}
