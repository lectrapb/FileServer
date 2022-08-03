package com.app.back.infraestructure.entrypoints;

import com.app.back.domain.model.filestorage.FileStorage;
import com.app.back.domain.usecase.filestorage.DownloadFileUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@AllArgsConstructor
public class DownloadFileController {


    private final DownloadFileUseCase useCase;

    @GetMapping("/api/download")
    public Mono<ResponseEntity<FileStorage>> download(@RequestParam String filename){

          Mono<FileStorage> response = useCase.download(filename);
          return response.map(p -> ResponseEntity
                                       .status(HttpStatus.OK)
                                       .contentType(MediaType.APPLICATION_JSON)
                                       .body(p));
    }
}
