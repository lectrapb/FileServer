package com.app.back.infraestructure.drivenadapter.mongo.adapter;

import com.app.back.domain.model.user.User;
import com.app.back.domain.model.user.gateways.UserService;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import com.app.back.infraestructure.drivenadapter.mongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserRepositoryImpl implements UserService {

    private UserRepository repository;

    @Autowired
    public UserRepositoryImpl(@Lazy UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<User> saveUser(User userUpdate) {
        return repository.insert(UserMapper.toData(userUpdate))
                .map(UserMapper::toModel);
    }

    @Override
    public Mono<UserEntity> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
