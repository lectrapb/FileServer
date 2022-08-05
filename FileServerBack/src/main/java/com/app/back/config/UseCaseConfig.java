package com.app.back.config;

import com.app.back.domain.usecase.filestorage.DeleteContentUseCase;
import com.app.back.domain.usecase.filestorage.DownloadFileUseCase;
import com.app.back.domain.usecase.filestorage.ListContentUseCase;
import com.app.back.domain.usecase.filestorage.UploadUseCase;
import com.app.back.infraestructure.drivenadapter.mongo.adapter.FileRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public UploadUseCase uploadUseCase(FileRepositoryAdapter filerepository){

        return new UploadUseCase(filerepository);
    }

    @Bean
    public DownloadFileUseCase downloadFileUseCase(FileRepositoryAdapter filerepository){

        return new DownloadFileUseCase(filerepository);
    }

    @Bean
    public ListContentUseCase listContentUseCase(FileRepositoryAdapter filerepository){

        return new ListContentUseCase(filerepository);
    }
    @Bean
    public DeleteContentUseCase deleteContentUseCase (FileRepositoryAdapter filerepository){

        return new DeleteContentUseCase(filerepository);
    }
}
