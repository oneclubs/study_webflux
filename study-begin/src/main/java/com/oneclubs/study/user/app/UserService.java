package com.oneclubs.study.user.app;

import com.oneclubs.study.user.entity.User;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Mono<User> getUser(String name) {
        return repository.findByName(name);
    }

    public Flux<User> getUsers() {
        return repository.findAll()
            .delayElements(Duration.ofSeconds(5));
    }
}
