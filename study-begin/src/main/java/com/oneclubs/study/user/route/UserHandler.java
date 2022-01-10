package com.oneclubs.study.user.route;

import com.oneclubs.study.user.app.UserService;
import com.oneclubs.study.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserService service;

    public Mono<ServerResponse> getUsers(ServerRequest request) {
        return ServerResponse.ok()
            .body(service.getUsers(), User.class);
    }

    public Mono<ServerResponse> getUser(ServerRequest request) {
        return ServerResponse.ok()
            .body(service.getUser(request.pathVariable("name")), User.class);
    }
}
