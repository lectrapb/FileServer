package com.app.back.domain.usecase.filestorage;


import com.app.back.config.BusinessException;
import com.app.back.domain.model.filestorage.gateways.FileRepositoryService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class DeleteContentUseCase {

    private final FileRepositoryService fileRepository;

    public Mono<Void> delete(String name){

        return Mono.fromCallable(() -> name)
                .onErrorResume(e -> Mono.error(new BusinessException("Name null")))
                .flatMap(nameFile -> fileRepository
                        .findByName(name)
                        .next()
                        .flatMap(fileStorage ->
                                fileRepository.deleteById(fileStorage.getId())));
    }
}
