package com.app.back.config;

import com.app.back.domain.model.user.gateways.PasswordEncryptService;
import com.app.back.domain.usecase.filestorage.UploadUseCase;
import com.app.back.infraestructure.drivenadapter.mongo.adapter.FileRepositoryAdapter;
import com.app.back.infraestructure.drivenadapter.security.adapter.PasswordAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public UploadUseCase uploadUseCase(FileRepositoryAdapter uploadFileAdapter){

        return new UploadUseCase(uploadFileAdapter);
    }

    @Bean
    public PasswordEncryptService passwordEncryptService(){
        return new PasswordAdapter();
    }
}
