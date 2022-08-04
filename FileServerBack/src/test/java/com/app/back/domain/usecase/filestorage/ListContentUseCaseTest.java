package com.app.back.domain.usecase.filestorage;

import com.app.back.domain.model.filestorage.gateways.FileRepositoryService;
import com.app.back.domain.usecase.domain.FileStorageMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListContentUseCaseTest {

     private static final int COUNT = 1;

     private FileRepositoryService fileRepository;

     private ListContentUseCase useCase;

    @BeforeEach
    void setUp() {
        fileRepository = mock(FileRepositoryService.class);
        useCase = new ListContentUseCase(fileRepository);
    }

    @Test
    void list_content_usecase_ok(){

        when(fileRepository.findAll()).thenReturn(FileStorageMother.dataFluxOk());
        useCase.listContent()
                .as(StepVerifier::create)
                .expectNextCount(2)
                .verifyComplete();
    }
}