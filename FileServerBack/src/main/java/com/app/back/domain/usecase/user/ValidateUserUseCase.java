package com.app.back.domain.usecase.user;

import com.app.back.domain.model.exception.BusinessException;
import com.app.back.domain.model.user.gateways.PasswordEncryptService;
import com.app.back.domain.model.user.gateways.UserService;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class ValidateUserUseCase {

    private PasswordEncryptService passwordEncryptService;
    private UserService userRepository;

    public Mono<UserEntity> validateUser(UserEntity user){
        UserEntity checkUsuario = new UserEntity();
         return userRepository.findByEmail(user.getEmail())
                 .switchIfEmpty(Mono.defer(() -> Mono.error(new BusinessException("EMAIL_NOT_EXIST","Email no existe"))))
                 .map(userEntity -> {
                     boolean check = passwordEncryptService.cleanPassword(user.getPassword(),userEntity.getPassword());
                     if (check){
                         checkUsuario.setUid(userEntity.getUid());
                         checkUsuario.setName(user.getName());
                         checkUsuario.setEmail(user.getEmail());
                         checkUsuario.setRole(user.getRole());
                         checkUsuario.setImage(user.getImage());
                         checkUsuario.setGoogle(user.getGoogle());
                     }
                     return checkUsuario;
                 });
    }
}
