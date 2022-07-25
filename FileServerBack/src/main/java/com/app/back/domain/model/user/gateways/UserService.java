package com.app.back.domain.model.user.gateways;

import com.app.back.domain.model.user.User;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import com.app.back.infraestructure.drivenadapter.mongo.repository.UserRepository;
import com.app.back.infraestructure.drivenadapter.mongo.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Mono<User> getUser(String id){
        return userRepository.findById(id).map(UserMapper::toModel);
    }

    public Mono<User> saveUser(Mono<User> userMono){
     return userMono.map(UserMapper::toData)
                .flatMap(userRepository::insert)
                .map(UserMapper::toModel);
    }
}
