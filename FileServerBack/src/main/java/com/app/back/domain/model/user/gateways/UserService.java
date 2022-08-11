package com.app.back.domain.model.user.gateways;

import com.app.back.domain.model.user.User;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> saveUser(User userUpdate);

    Mono<UserEntity> findByEmail(String email);
}
