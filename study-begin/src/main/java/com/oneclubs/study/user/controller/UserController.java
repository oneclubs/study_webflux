package com.oneclubs.study.user.controller;

import com.oneclubs.study.user.app.UserService;
import com.oneclubs.study.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @GetMapping("/{name}")
    public Mono<User> getUser(@PathVariable("name") String name) {
        return service.getUser(name);
    }

    @GetMapping
    public Flux<User> getUsers() {
        return service.getUsers();
    }
}
