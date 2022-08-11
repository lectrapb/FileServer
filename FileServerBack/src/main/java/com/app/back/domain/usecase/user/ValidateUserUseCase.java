package com.app.back.domain.usecase.user;

import com.app.back.domain.model.user.User;
import com.app.back.domain.model.user.gateways.UserService;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class ValidateUserUseCase {

    private UserService userRepository;

    public Mono<UserEntity> validateUser(UserEntity user){
         return userRepository.findByEmail(user.getEmail());
    }
}
