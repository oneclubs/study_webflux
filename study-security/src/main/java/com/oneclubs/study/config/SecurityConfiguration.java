package com.oneclubs.study.config;

import com.oneclubs.study.filter.StudyAuthenticationFilter;
import com.oneclubs.study.jwt.JwtAuthenticationConverter;
import com.oneclubs.study.jwt.JwtService;
import com.oneclubs.study.jwt.JwtTokenConverter;
import com.oneclubs.study.manager.BearerTokenReactiveAuthenticationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

    @Configuration(proxyBeanMethods = false)
    static class FilterConfiguration {

        @Bean
        StudyAuthenticationFilter authenticationFilter(JwtService jwtService, JwtTokenConverter jwtTokenConverter) {

            StudyAuthenticationFilter filter = new StudyAuthenticationFilter(new BearerTokenReactiveAuthenticationManager());

            filter.setServerAuthenticationConverter(new JwtAuthenticationConverter(jwtService, jwtTokenConverter));
            filter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/**"));

            return filter;
        }
    }

    @Configuration(proxyBeanMethods = false)
    static class FilterChainConfiguration {

        private final StudyAuthenticationFilter authenticationFilter;

        FilterChainConfiguration(StudyAuthenticationFilter authenticationFilter) {
            this.authenticationFilter = authenticationFilter;
        }

        @Bean
        public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

            // @formatter:off
            http
                .authorizeExchange()
                    .pathMatchers("/", "/user/login").permitAll()
                    .pathMatchers(HttpMethod.OPTIONS).permitAll()
                    .pathMatchers(HttpMethod.GET).permitAll()
                    .pathMatchers(HttpMethod.POST).permitAll()
                    .pathMatchers("/**").authenticated()
                    .and()
                .addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            ;
            // @formatter:on

            return http.build();
        }
    }
}
