package com.hjy.handlers;

import com.hjy.domain.User;
import com.hjy.repository.UserRepository;
import com.hjy.util.CheckUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @auther: hjy
 * @Date: 2021/6/29 14:03
 * @Description:
 */
@Component
public class UserHandler {

    private final UserRepository repository;

    public UserHandler(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * 得到所有用户
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllUser(ServerRequest request){
        return ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(repository.findAll(), User.class);
    }


    /**
     * 创建用户
     * @param request
     * @return
     */
    public Mono<ServerResponse> createUser(ServerRequest request){
        Mono<User> userMono = request.bodyToMono(User.class);
        return userMono.flatMap(u->{
            CheckUtil.checkName(u.getName());
            return ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(repository.save(u), User.class);
        });
    }

    /**
     * 根据id删除用户
     * @param request
     * @return
     */
    public Mono<ServerResponse> deleteUserById(ServerRequest request){
        String id = request.pathVariable("id");
        return repository.findById(id).flatMap(user -> repository.delete(user).then(ok().build()))
                .switchIfEmpty(notFound().build());
    }

}
