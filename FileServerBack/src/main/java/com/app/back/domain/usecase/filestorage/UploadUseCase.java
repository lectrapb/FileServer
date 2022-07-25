package com.app.back.domain.usecase.filestorage;

import com.app.back.domain.model.filestorage.FileStorage;
import com.app.back.domain.model.filestorage.FileStorageUploadResponseDTO;
import com.app.back.domain.model.filestorage.gateways.FileRepositoryService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Date;

@AllArgsConstructor
public class UploadUseCase {

    private final FileRepositoryService fileRepository;

    public Mono<FileStorageUploadResponseDTO> upload(FileStorage file){

        return Mono.fromCallable(()-> file)
                .onErrorResume(e -> Mono.error(e))
                .map(fileOrigin -> {
                     fileOrigin.setCreateAt(new Date());
                     return fileOrigin;
                })
                .flatMap(fileDate -> fileRepository.save(fileDate)
                                    .flatMap(fileMono ->
                                            Mono.fromCallable(FileStorageUploadResponseDTO::new)
                                           .map( fileDTO ->{
                                                 fileDTO.setId(fileMono.getId());
                                                 fileDTO.setName(fileMono.getName());
                                                 fileDTO.setType(fileMono.getType());
                                                 return fileDTO;
                                           })));


    }
}
