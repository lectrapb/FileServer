package com.app.back.domain.model.filestorage;


import lombok.Data;

@Data
public class FileStorageUploadResponseDTO {

    private String id;
    private String name;
    private String type;
    private String error;
}
