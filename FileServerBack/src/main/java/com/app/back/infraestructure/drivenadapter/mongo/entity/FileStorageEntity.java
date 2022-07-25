package com.app.back.infraestructure.drivenadapter.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
@Data
public class FileStorageEntity {

    @Id
    private String id;
    private String name;
    private String type;
    private Date createAt;
    private String content;
}
