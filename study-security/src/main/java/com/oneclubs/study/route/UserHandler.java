package com.oneclubs.study.route;

import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.oneclubs.study.jwt.JwtService;
import com.oneclubs.study.jwt.JwtUserAuthenticationToken;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserHandler {

    private final JwtService jwtService;

    public Mono<ServerResponse> login(ServerRequest request) {
        return ok()
            .header(HttpHeaders.AUTHORIZATION, jwtService.create(request.queryParam("id").orElse("anonymous")))
            .bodyValue("ok");
    }

    public Mono<ServerResponse> hello(ServerRequest request) {

        return ReactiveSecurityContextHolder.getContext()
            .map(SecurityContext::getAuthentication)
            .flatMap(authentication -> ok()
                .bodyValue("Hello " + authentication.getPrincipal()))
            .switchIfEmpty(
                badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue("wrong authentication")
            );
    }
}
