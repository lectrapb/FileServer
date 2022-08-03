package com.app.back.config;

import com.app.back.domain.usecase.filestorage.DownloadFileUseCase;
import com.app.back.domain.usecase.filestorage.UploadUseCase;
import com.app.back.infraestructure.drivenadapter.mongo.adapter.FileRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public UploadUseCase uploadUseCase(FileRepositoryAdapter uploadFileAdapter){

        return new UploadUseCase(uploadFileAdapter);
    }

    @Bean
    public DownloadFileUseCase downloadFileUseCase(FileRepositoryAdapter filedapter){

        return new DownloadFileUseCase(filedapter);
    }
}
