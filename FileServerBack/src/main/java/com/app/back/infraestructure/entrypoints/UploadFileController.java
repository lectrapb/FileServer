package com.app.back.infraestructure.entrypoints;


import com.app.back.domain.model.filestorage.FileStorage;
import com.app.back.domain.model.filestorage.FileStorageUploadResponseDTO;
import com.app.back.domain.usecase.filestorage.UploadUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class UploadFileController {

    private final UploadUseCase uploadUseCase;

    private final ReactiveGridFsTemplate gridFsTemplate;

    @PostMapping(value = "/api/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public Mono<ResponseEntity<FileStorageUploadResponseDTO>> upload(@RequestPart("file") Mono<FilePart> filePart) {

        Mono<FileStorage> fileStorageMono  = filePart
                .map(fileP -> {
                     FileStorage fileStorage = new FileStorage();
                     fileStorage.setName(fileP.filename());
                     fileStorage.setType(fileP.headers().getContentType().toString());
                     return  fileStorage;
                });


        Mono<FileStorageUploadResponseDTO> responseMono = filePart
                .map(data1 -> data1)
                .flatMap(part -> gridFsTemplate.store(part.content(), part.filename()))
                .zipWith(fileStorageMono,(objectId,fileStorage ) ->{
                      FileStorage fileRegistry = new FileStorage();
                      fileRegistry.setName(fileStorage.getName());
                      fileRegistry.setType(fileStorage.getType());
                      fileRegistry.setContent(objectId.toString());
                      return fileRegistry;
                })
                .flatMap(uploadUseCase::upload);

        return responseMono.map(p -> ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(p));
    }

}