package com.app.back.infraestructure.entrypoints;


import com.app.back.domain.usecase.filestorage.DeleteContentUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
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

    private final ReactiveGridFsTemplate gridFsTemplate;
     @DeleteMapping("/api/delete")
    public Mono<ResponseEntity<Void>> delete(@RequestParam("namefile") String nameFile){

         Query query = new Query(GridFsCriteria.whereFilename().is(nameFile));
       //  var result = useCase.delete(nameFile)
       //          .then(Mono.just(new ResponseEntity<>(HttpStatus.NO_CONTENT)));
         var result =  gridFsTemplate.delete(query)
                 .then(useCase.delete(nameFile))
                 .then( Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))  );
         return result;
     }

}
