package com.oneclubs.study.config;

import com.oneclubs.study.jwt.JwtService;
import com.oneclubs.study.jwt.JwtTokenConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class JwtConfiguration {

    @Bean
    JwtService jwtService() {
        return new JwtService();
    }

    @Bean
    JwtTokenConverter jwtTokenConverter() {
        return new JwtTokenConverter();
    }
}
