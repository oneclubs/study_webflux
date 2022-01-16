package com.oneclubs.study.jwt;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public record JwtAuthenticationConverter(
    JwtService jwtService,
    JwtTokenConverter jwtTokenConverter)
    implements ServerAuthenticationConverter {

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange)
            .flatMap(serverWebExchange -> Mono.justOrEmpty(
                    serverWebExchange.getRequest()
                        .getHeaders()
                        .getFirst(HttpHeaders.AUTHORIZATION)
                )
            )
            .filter(value -> value.length() > "Bearer ".length())
            .flatMap(value -> Mono.justOrEmpty(value.substring("Bearer ".length())))
            .flatMap(jwtService::convertJwt)
            .flatMap(jwtTokenConverter::convert)
            .log();
    }
}
