package com.app.back.infraestructure.drivenadapter.mongo.repository;

import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<UserEntity,String> {
}
