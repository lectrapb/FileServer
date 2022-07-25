package com.app.back.infraestructure.drivenadapter.mongo.repository;

import com.app.back.domain.model.user.User;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserEntity,String> {
}
