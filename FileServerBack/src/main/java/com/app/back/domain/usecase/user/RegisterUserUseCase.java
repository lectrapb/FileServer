package com.app.back.domain.usecase.user;

import com.app.back.domain.model.exception.BusinessException;
import com.app.back.domain.model.user.gateways.PasswordEncryptService;
import com.app.back.domain.model.user.gateways.UserService;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@AllArgsConstructor
public class RegisterUserUseCase {

    private PasswordEncryptService passwordEncryptService;
    private UserService userRepository;

    public Mono<UserEntity> register(UserEntity user) {
        return Mono.fromCallable(()->user)
                .onErrorResume(e -> Mono.error(e))
                .flatMap(userUpdate -> userRepository.saveUser(userUpdate));
    }
}