package com.app.back.domain.usecase.filestorage;

import com.app.back.domain.model.filestorage.FileStorage;
import com.app.back.domain.model.filestorage.gateways.FileRepositoryService;
import com.app.back.domain.usecase.domain.FileStorageMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UploadUseCaseTest {


    private static final int COUNT = 1;
    private FileRepositoryService fileRepository;
    private UploadUseCase useCase;


    @BeforeEach
    void setUp() {
        fileRepository = mock(FileRepositoryService.class);
        useCase = new UploadUseCase(fileRepository);
    }

    @Test
    void upload_file_usecase_null() {

        useCase.upload(null)
                .as(StepVerifier::create)
                .expectNext()
                .verifyComplete();

        System.out.println("end test download_file_usecase_null()");
    }

    @Test
    void upload_file_usecase_ok() {


        when(this.fileRepository.findById(anyString())).thenReturn(FileStorageMother.dataMonoOk());
        useCase.upload(new FileStorage())
                .as(StepVerifier::create)
                .expectNextCount(COUNT)
                .verifyComplete();

        System.out.println("end test download_file_usecase_ok");
    }


}