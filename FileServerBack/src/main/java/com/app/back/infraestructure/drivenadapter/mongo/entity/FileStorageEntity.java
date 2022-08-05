package com.app.back.infraestructure.drivenadapter.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document("files")
public class FileStorageEntity {

    @Id
    private String id;
    private String name;
    private String type;
    private Date createAt;
    private String content;
}
