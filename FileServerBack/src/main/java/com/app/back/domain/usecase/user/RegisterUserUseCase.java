package com.app.back.domain.usecase.user;

import com.app.back.domain.model.user.User;
import com.app.back.domain.model.user.gateways.PasswordEncryptService;
import com.app.back.domain.model.user.gateways.UserService;
import com.app.back.infraestructure.drivenadapter.mongo.adapter.UserMapper;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@AllArgsConstructor
public class RegisterUserUseCase {

    private PasswordEncryptService passwordEncryptService;
    private UserService userRepository;

    public Mono<User> register(User user) {
        UserEntity userEnt = UserMapper.toData(user);
        Mono<UserEntity> userExist = userRepository.findByEmail(userEnt.getEmail());
        if(userExist != null){
            throw new RuntimeException("User Exist");
        }
        return Mono.fromCallable(()->user)
                .onErrorResume(e -> Mono.error(e))
                .map(userIniciate -> {
                    userIniciate.setPassword(passwordEncryptService.encryptPassword(userIniciate.getPassword()));
                    return userIniciate;
                })
                .flatMap(userUpdate -> userRepository.saveUser(userUpdate));

    }
}
