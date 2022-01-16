package com.oneclubs.study.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

public class JwtTokenConverter {

    Mono<Authentication> convert(DecodedJWT decoded) {
        try {
            return Mono.justOrEmpty(new JwtUserAuthenticationToken(decoded));
        } catch (Exception e) {
            return Mono.empty();
        }
    }
}
