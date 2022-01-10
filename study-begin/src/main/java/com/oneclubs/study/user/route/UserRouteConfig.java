package com.oneclubs.study.user.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration(proxyBeanMethods = false)
public class UserRouteConfig {

    @Bean
    public RouterFunction<?> userRoute(UserHandler handler) {
        return RouterFunctions.route().nest(RequestPredicates.accept(MediaType.APPLICATION_JSON), nested ->
            nested.path("/user-route", builder ->
                builder
                    .GET("", handler::getUsers)
                    .GET("/{name}", handler::getUser)
            )
        ).build();
    }
}
