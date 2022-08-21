package com.app.back.infraestructure.drivenadapter.mongo.entity;

import com.app.back.domain.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
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
    private String error;


    public void initId(){
        this.uid = UUID.randomUUID().toString();
    }
}
