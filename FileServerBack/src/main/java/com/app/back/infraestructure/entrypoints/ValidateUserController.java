package com.app.back.infraestructure.entrypoints;

import com.app.back.domain.usecase.user.ValidateUserUseCase;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/validate")
public class ValidateUserController {

    @Autowired
    private ValidateUserUseCase validateUserUseCase;

    @PostMapping
    public Mono<UserEntity> validateUser(@RequestBody Mono<UserEntity> userMono){
        return userMono.map(userEntity -> userEntity)
                .flatMap(user ->validateUserUseCase.validateUser(user));
    }
}
