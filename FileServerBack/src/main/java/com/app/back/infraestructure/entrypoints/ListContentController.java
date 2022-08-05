package com.app.back.infraestructure.entrypoints;


import com.app.back.domain.model.filestorage.FileStorage;
import com.app.back.domain.usecase.filestorage.ListContentUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class ListContentController {

    private final ListContentUseCase useCase;

    @GetMapping("/api/listcontent")
    public Mono<ResponseEntity<Flux<FileStorage>>>listContent(){

        Flux<FileStorage> listContent = useCase.listContent();
        return Mono.just(ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(listContent));
    }
}
