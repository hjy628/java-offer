package com.hjy;

import com.h.ApiServer;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @auther: hjy
 * @Date: 2021/6/30 10:22
 * @Description:
 */

@ApiServer("http://localhost:8080/user")
public interface IUserApi {


    @GetMapping("/")
    Flux<User> getAllUsers();


    @GetMapping("/{id}")
    Mono<User> getUserById(@PathVariable("id") String id);


    @DeleteMapping("/{id}")
    Mono<Void> deleteUserById(@PathVariable("id") String id);


    @PostMapping("/")
    Flux<User> createUser(@RequestBody Mono<User> user);
}
