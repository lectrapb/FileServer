package com.app.back.domain.model.user.gateways;

import com.app.back.domain.model.user.User;
import reactor.core.publisher.Mono;

public interface UserServiceOne {
    Mono<User> save(User userUpdate);
}
