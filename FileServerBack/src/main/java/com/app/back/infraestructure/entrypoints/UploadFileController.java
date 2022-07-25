package com.app.back.infraestructure.entrypoints;


import com.app.back.domain.model.filestorage.FileStorage;
import com.app.back.domain.model.filestorage.FileStorageUploadResponseDTO;
import com.app.back.domain.usecase.filestorage.UploadUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class UploadFileController {

    private final UploadUseCase uploadUseCase;

    @RequestMapping(path = "/api/upload", method = RequestMethod.POST, consumes =
            { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public Mono<ResponseEntity<FileStorageUploadResponseDTO>> upload(@RequestPart("file") Mono<FilePart> filePart) {

        Mono<FileStorageUploadResponseDTO> responseMono = filePart
                .map(data1 -> data1)
                .flatMap(fileDto ->{
                    FileStorage fileStorage=  new FileStorage();
                    fileStorage.setName(fileDto.filename());
                    fileStorage.setType(fileDto.headers().getContentType().toString());
                    return this.uploadUseCase.upload(fileStorage);
                 });

        return responseMono.map(p -> ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(p));
    }

}