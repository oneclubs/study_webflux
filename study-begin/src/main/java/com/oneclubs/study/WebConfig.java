package com.oneclubs.study;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.WebFilter;

@Configuration(proxyBeanMethods = false)
public class WebConfig {

    @Value("classpath:/static/index.html")
    private Resource indexPage;

    @Bean
    public RouterFunction<ServerResponse> indexRouter() {
        return RouterFunctions.route(
            RequestPredicates.GET("/"),
            request ->
                ServerResponse.ok()
                    .contentType(MediaType.TEXT_HTML)
                    .bodyValue(indexPage)
        );

    }
}
