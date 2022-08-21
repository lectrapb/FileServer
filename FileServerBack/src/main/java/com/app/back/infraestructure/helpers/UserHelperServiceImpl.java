package com.app.back.infraestructure.helpers;

import com.app.back.domain.model.exception.BusinessException;
import com.app.back.domain.model.user.gateways.PasswordEncryptService;
import com.app.back.domain.model.user.gateways.UserService;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class UserHelperServiceImpl implements UserHelperService{

    private PasswordEncryptService passwordEncryptService;
    private UserService userRepository;

    @Override
    public Mono<UserEntity> registrarUser(UserEntity userEntity) {
        var consulta = userRepository.findByEmail(userEntity.getEmail())
                .next()
                .flatMap(userEnt-> Mono.error(new BusinessException("EMAIL_EXIST","Email ya existe")))
                .onErrorResume(e ->Mono.error(new com.app.back.config.BusinessException(e.getMessage())))
                .switchIfEmpty(Mono.fromCallable(() -> new UserEntity(userEntity.getUid(),userEntity.getName(), userEntity.getEmail(),
                        userEntity.getPassword(), userEntity.getImage(), userEntity.getRole(), userEntity.getGoogle(), userEntity.getError())))
                .map(object -> {
                    UserEntity userIniciate =(UserEntity)object;
                    userIniciate.setPassword(passwordEncryptService.encryptPassword(userIniciate.getPassword()));
                    return userIniciate;
                });
        return consulta;
    }
}
