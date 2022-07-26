package com.app.back.infraestructure.drivenadapter.security.algoritms;


import com.app.back.domain.model.user.gateways.PasswordEncryptService;

public class ByCryptImpl implements PasswordEncryptService {
    @Override
    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    @Override
    public boolean cleanPassword(String currentPass, String encryptPass) {
        return BCrypt.checkpw(currentPass,encryptPass);
    }
}
