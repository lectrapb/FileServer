package com.app.back.domain.usecase.user;

import com.app.back.domain.model.user.User;
import com.app.back.domain.model.user.gateways.PasswordEncryptService;
import com.app.back.domain.model.user.gateways.UserServiceOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;


@AllArgsConstructor
public class RegisterUserUseCase {

    private PasswordEncryptService passwordEncryptService;
    private UserServiceOne userRepository;

    public Mono<User> register(User user){
        return Mono.fromCallable(()->user)
                .onErrorResume(e -> Mono.error(e))
                .map(userIniciate -> {
                    userIniciate.setPassword(passwordEncryptService.encryptPassword(userIniciate.getPassword()));
                    return userIniciate;
                })
                .flatMap(userUpdate -> userRepository.save(userUpdate));

    }
}
