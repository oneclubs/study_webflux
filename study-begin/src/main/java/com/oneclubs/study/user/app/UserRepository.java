package com.oneclubs.study.user.app;

import com.oneclubs.study.user.entity.User;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserRepository {

    private static final Map<String, User> users = new HashMap<>();
    static {

        users.put("john", User.builder().name("john").age(10).build());
        users.put("rian", User.builder().name("rian").age(20).build());
    }

    public Flux<User> findAll() {
        return Flux.fromIterable(users.values());
    }

    public Mono<User> findByName(String name) {
        return Mono.just(users.get(name));
    }
}
