package com.app.back.domain.usecase.domain;

import com.app.back.domain.model.filestorage.FileStorage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

public class FileStorageMother {


    public static FileStorage dataOk(){
        FileStorage file = new FileStorage();
        file.setId(UUID.randomUUID().toString());
        file.setName("fila-test");
        file.setCreateAt(new Date());
        file.setContent("C://");
        file.setType(".pdf");
        return file;
    }

    public static Mono<FileStorage> dataMonoOk(){
        FileStorage file = new FileStorage();
        file.setId(UUID.randomUUID().toString());
        file.setName("fila-test");
        file.setCreateAt(new Date());
        file.setContent("C://");
        file.setType(".pdf");
        return Mono.fromCallable(()-> file);
    }

    public static Flux<FileStorage> dataFluxOk() {

         return Flux.just(new FileStorage(), new FileStorage());
    }
}
