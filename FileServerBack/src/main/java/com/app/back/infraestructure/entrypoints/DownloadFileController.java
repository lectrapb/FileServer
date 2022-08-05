package com.app.back.infraestructure.entrypoints;

import com.app.back.domain.usecase.filestorage.DownloadFileUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


@RestController
@AllArgsConstructor
public class DownloadFileController {


    private final DownloadFileUseCase useCase;

    private final ReactiveGridFsTemplate gridFsTemplate;

      @GetMapping("/api/preview")
      public Flux<Void> read2(@RequestParam("filename") String filename, ServerWebExchange exchange) {


        var result = useCase.download(filename)
                .map(fileStorage -> fileStorage.getContent())
                .flatMap(id -> gridFsTemplate.findOne(query(where("_id").is(id))))
                .flatMap(gridFSFile -> gridFsTemplate.getResource(gridFSFile))
                .flatMapMany(r -> exchange.getResponse().writeWith(r.getDownloadStream()));

       return result;
    }

    @GetMapping("/api/download")
    public Flux<Void> read3(@RequestParam("filename") String filename, ServerWebExchange exchange) {


        var result = useCase.download(filename)
                .map(fileStorage -> fileStorage.getContent())
                .flatMap(id -> gridFsTemplate.findOne(query(where("_id").is(id))))
                .flatMap(gridFSFile -> gridFsTemplate.getResource(gridFSFile))
                .flatMapMany(r ->{
                            ServerHttpResponse originalResponse = exchange.getResponse();
                            originalResponse.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+r.getFilename()+"");
                            originalResponse.getHeaders().setContentType(MediaType.APPLICATION_OCTET_STREAM);
                            return  exchange.getResponse().writeWith(r.getDownloadStream());
                            }
                        );
        return result;
    }
}
