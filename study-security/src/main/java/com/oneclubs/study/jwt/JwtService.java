package com.oneclubs.study.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import reactor.core.publisher.Mono;

public class JwtService {

    public String create(String id) {
        return JWT.create()
            .withClaim("type", "type")
            .withClaim("userId", id)
            .sign(Algorithm.HMAC256("secret-key"));
    }

    public Mono<DecodedJWT> convertJwt(String tokenValue) {
        return Mono.just(
                JWT.require(Algorithm.HMAC256("secret-key")).build()
                    .verify(tokenValue))
            .log()
            .onErrorResume(e -> Mono.error(new RuntimeException("Invalid Token")));
    }
}
