package com.app.back.infraestructure.drivenadapter.mongo.repository;

import com.app.back.infraestructure.drivenadapter.mongo.entity.FileStorageEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FileRepository extends ReactiveMongoRepository<FileStorageEntity, String> {
}
