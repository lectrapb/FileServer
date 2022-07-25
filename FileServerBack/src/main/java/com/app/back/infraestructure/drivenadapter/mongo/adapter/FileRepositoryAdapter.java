package com.app.back.infraestructure.drivenadapter.mongo.adapter;

import com.app.back.domain.model.filestorage.FileStorage;
import com.app.back.domain.model.filestorage.gateways.FileRepositoryService;
import com.app.back.infraestructure.drivenadapter.mongo.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public class FileRepositoryAdapter implements FileRepositoryService {

    private final FileRepository repository;

    @Autowired
    public FileRepositoryAdapter(FileRepository repository) {
        this.repository = repository;
    }
    @Override
    public Mono<FileStorage> save(FileStorage fileStorage) {
        return repository
                .insert(MapperFileStorage.toEntity(fileStorage))
                .map(MapperFileStorage::toModel);
    }

    @Override
    public Mono<FileStorage> findById(String id) {
        return null;
    }
}
