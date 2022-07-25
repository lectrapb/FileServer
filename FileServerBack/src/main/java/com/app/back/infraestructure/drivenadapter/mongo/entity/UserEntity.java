package com.app.back.infraestructure.drivenadapter.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserEntity {

    @Id
    private String uid;
    private String name;
    private String email;
    private String password;
    private String image;
    private String role;
    private Boolean google = false;


    public void initId(){
        this.uid = UUID.randomUUID().toString();
    }
}
