package com.app.back.infraestructure.drivenadapter.mongo.repository;

import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveMongoRepository<UserEntity,String> {

    @Query("{email:'?0'}")
    Flux<UserEntity> findByEmail(String email);
}
