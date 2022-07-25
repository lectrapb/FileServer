package com.app.back.domain.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String uid;
    private String name;
    private String email;
    private String password;
    private String image;
    private String role;
    private boolean google;

}
