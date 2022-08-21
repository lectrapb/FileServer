package com.app.back.infraestructure.entrypoints;

import com.app.back.config.BusinessException;
import com.app.back.domain.model.user.User;
import com.app.back.domain.usecase.user.RegisterUserUseCase;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import com.app.back.infraestructure.helpers.UserHelperService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping("/users")
public class RegisterUserController {

    @Autowired
    private RegisterUserUseCase registerUserUseCase;
    private UserHelperService userHelperService;

    @PostMapping
    public Mono<ResponseEntity<UserEntity>> saveUser(@RequestBody Mono<UserEntity> userMono) {
         Mono<UserEntity> userRegisterResponse = userMono
                 .flatMap(user -> userHelperService.registrarUser(user))
                 .onErrorResume(e -> Mono.error(new BusinessException(e.getMessage())))
                 .flatMap(user1 -> registerUserUseCase.register(user1));

        return userRegisterResponse.map(responseRegisterUser -> ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseRegisterUser))
                .onErrorResume(error -> Mono.just(error)
                        .flatMap(e->{
                            UserEntity userMessageRegister = new UserEntity();
                            userMessageRegister.setError(e.getMessage());
                            return Mono.just(ResponseEntity.badRequest().body(userMessageRegister));
                        }));
    }
}
