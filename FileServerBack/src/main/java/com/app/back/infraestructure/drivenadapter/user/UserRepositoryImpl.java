package com.app.back.infraestructure.drivenadapter.user;

import com.app.back.domain.model.user.User;
import com.app.back.domain.model.user.gateways.UserServiceOne;
import com.app.back.infraestructure.drivenadapter.mongo.repository.UserRepository;
import com.app.back.infraestructure.drivenadapter.mongo.utils.UserMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserRepositoryImpl implements UserServiceOne {

    private UserRepository userRepository;

    @Autowired
    public UserRepositoryImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> save(User userUpdate) {
        return userRepository.insert(UserMapper.toData(userUpdate))
                .map(UserMapper::toModel);
    }
}
