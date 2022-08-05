package com.app.back.infraestructure.entrypoints;


import com.app.back.domain.usecase.filestorage.DeleteContentUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class DeleteContentController {


     public final DeleteContentUseCase useCase;

     @DeleteMapping("/api/delete")
    public Mono<ResponseEntity<Void>> delete(@RequestParam("namefile") String nameFile){

         return useCase.delete(nameFile)
                 .then(Mono.just(new ResponseEntity<>(HttpStatus.NO_CONTENT)));
     }

}
