package com.app.back.infraestructure.helpers;

import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import reactor.core.publisher.Mono;

public interface UserHelperService {

    Mono<UserEntity> registrarUser(UserEntity userValidate);
}
