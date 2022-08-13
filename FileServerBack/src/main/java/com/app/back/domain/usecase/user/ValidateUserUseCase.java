package com.app.back.domain.usecase.user;

import com.app.back.domain.model.exception.BusinessException;
import com.app.back.domain.model.user.gateways.UserService;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class ValidateUserUseCase {

    private UserService userRepository;

    public Mono<UserEntity> validateUser(UserEntity user){
         return userRepository.findByEmail(user.getEmail())
                 .switchIfEmpty(Mono.defer(() -> Mono.error(new BusinessException("EMAIL_NOT_EXIST","Email no existe"))));
                 //.onErrorMap(error -> new BusinessException("ERROR_NOT_FOUND","Error en la transaccion" + error.getMessage(), error));
    }
}
