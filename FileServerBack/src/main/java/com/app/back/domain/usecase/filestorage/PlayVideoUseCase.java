package com.app.back.domain.usecase.filestorage;


import com.app.back.domain.model.filestorage.FileStorage;
import com.app.back.domain.model.filestorage.gateways.FileRepositoryService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class PlayVideoUseCase {

    private final FileRepositoryService fileRepository;


    public Mono<FileStorage> play(String nameVideo){

          return fileRepository.findByName(nameVideo).next();
    }
}
