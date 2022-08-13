package com.app.back.domain.model.user.gateways;

public interface PasswordEncryptService {
    String encryptPassword(String password);
    boolean cleanPassword(String currentPass, String encryptPass);
}
