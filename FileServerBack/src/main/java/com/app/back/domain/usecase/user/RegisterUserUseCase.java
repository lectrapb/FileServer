package com.app.back.domain.usecase.user;

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
        UserEntity userEntity1 = new UserEntity();
        Mono<UserEntity> consulta = userRepository.findByEmail(user.getEmail());
        return Mono.fromCallable(()->user)
                .onErrorResume(e -> Mono.error(e))
                .map(userIniciate -> {
                    userIniciate.setPassword(passwordEncryptService.encryptPassword(userIniciate.getPassword()));
                    return userIniciate;
                })
                .flatMap(userUpdate -> userRepository.saveUser(userUpdate));
    }
}