package com.app.back.domain.usecase.filestorage;

import com.app.back.config.BusinessException;
import com.app.back.domain.model.filestorage.FileStorage;
import com.app.back.domain.model.filestorage.gateways.FileRepositoryService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@AllArgsConstructor
public class DownloadFileUseCase {

    private final FileRepositoryService fileRepository;

    public Mono<FileStorage> download(String fileName){

          return Mono.fromCallable(() -> fileName)
                  .onErrorResume( e -> Mono.error(new BusinessException("File name null")))
                  .flatMap(fileNameOk -> fileRepository.findByName(fileName)
                                        .next());
    }
}
