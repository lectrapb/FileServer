package com.app.back.infraestructure.entrypoints;

import com.app.back.domain.usecase.user.ValidateUserUseCase;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public Mono<ResponseEntity<UserEntity>> validateUser(@RequestBody Mono<UserEntity> userResponse){
        Mono<UserEntity> userMono = userResponse.map(userEntity -> userEntity)
                .flatMap(user ->validateUserUseCase.validateUser(user));

        return userMono.map(responseUser -> ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseUser))
                .onErrorResume(error -> Mono.just(error)
                        .flatMap(e->{
                            UserEntity userMessage = new UserEntity();
                            userMessage.setError(e.getMessage());
                            return Mono.just(ResponseEntity.badRequest().body(userMessage));
                        }));
    }
}
