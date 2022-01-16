package com.oneclubs.study.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.oneclubs.study.jwt.JwtService;
import com.oneclubs.study.route.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration(proxyBeanMethods = false)
public class UserRouteConfiguration {

    @Bean
    UserHandler userHandler(JwtService jwtService) {
        return new UserHandler(jwtService);
    }

    @Bean
    public RouterFunction<?> userRoute(UserHandler userHandler) {
        return route()
            .nest(accept(MediaType.APPLICATION_JSON), nested ->
                nested.path("/user", builder -> builder
                    .GET("/login", userHandler::login)
                    .GET("/hello", userHandler::hello)
                )
            ).build();
    }
}
