package com.app.back.domain.usecase.filestorage;

import com.app.back.domain.model.filestorage.gateways.FileRepositoryService;
import com.app.back.domain.usecase.domain.FileStorageMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DownloadFileUseCaseTest {

     private static final int COUNT = 1;
    private FileRepositoryService fileRepository;

    private DownloadFileUseCase useCase;

    @BeforeEach
    void setUp() {

        fileRepository  = mock(FileRepositoryService.class);
        useCase = new DownloadFileUseCase(fileRepository);
    }


    @Test
    void download_file_usecase_null(){

         useCase.download(null)
                 .as(StepVerifier::create)
                 .expectNext()
                 .verifyComplete();
    }

    @Test
    void download_file_usecase_ok(){

        when(fileRepository.findByName(anyString())).thenReturn(FileStorageMother.dataFluxOk());
        useCase.download("file-name")
                .as(StepVerifier:: create)
                .expectNextCount(0)
                .verifyError();
    }

}