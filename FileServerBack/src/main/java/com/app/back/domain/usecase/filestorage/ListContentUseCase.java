package com.app.back.domain.usecase.filestorage;


import com.app.back.domain.model.filestorage.FileStorage;
import com.app.back.domain.model.filestorage.gateways.FileRepositoryService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@AllArgsConstructor
public class ListContentUseCase {

    private final FileRepositoryService fileRepository;

    public Flux<FileStorage> listContent(){

         return fileRepository.findAll();
    }
}
