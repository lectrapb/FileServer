package com.app.back.infraestructure.drivenadapter.mongo.adapter;

import com.app.back.domain.model.user.gateways.UserService;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import com.app.back.infraestructure.drivenadapter.mongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserRepositoryImpl implements UserService {

    private UserRepository repository;

    @Autowired
    public UserRepositoryImpl(@Lazy UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<UserEntity> saveUser(UserEntity userUpdate) {
        return repository.insert(userUpdate);
    }

    @Override
    public Flux<UserEntity> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
