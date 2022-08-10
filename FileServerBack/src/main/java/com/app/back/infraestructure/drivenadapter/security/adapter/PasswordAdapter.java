package com.app.back.infraestructure.drivenadapter.security.adapter;

import com.app.back.domain.model.user.gateways.PasswordEncryptService;
import com.app.back.infraestructure.drivenadapter.security.algoritms.ByCryptImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PasswordAdapter implements PasswordEncryptService{

    private PasswordEncryptService passwordEncryptService;

    @Autowired
    public PasswordAdapter(){
        passwordEncryptService = new ByCryptImpl();
    }

    @Override
    public String encryptPassword(String password) {
        System.out.println("Hola");
        return passwordEncryptService.encryptPassword(password);
    }
}