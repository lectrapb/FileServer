package com.app.back.config;

import com.app.back.domain.model.user.User;
import com.app.back.domain.model.user.gateways.PasswordEncryptService;
import com.app.back.domain.usecase.filestorage.UploadUseCase;
import com.app.back.domain.usecase.user.RegisterUserUseCase;
import com.app.back.domain.usecase.user.ValidateUserUseCase;
import com.app.back.infraestructure.drivenadapter.mongo.adapter.FileRepositoryAdapter;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import com.app.back.infraestructure.drivenadapter.security.adapter.PasswordAdapter;
import com.app.back.infraestructure.drivenadapter.mongo.adapter.UserRepositoryImpl;
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

    @Bean
    public RegisterUserUseCase registerUserUseCase(PasswordAdapter passwordAdapter,UserRepositoryImpl userRepository){
        return new RegisterUserUseCase(passwordAdapter,userRepository);
    }

    @Bean
    public ValidateUserUseCase validateUserUseCase(PasswordAdapter passwordAdapter,UserRepositoryImpl user){
        return new ValidateUserUseCase(passwordAdapter, user);
    }
}
