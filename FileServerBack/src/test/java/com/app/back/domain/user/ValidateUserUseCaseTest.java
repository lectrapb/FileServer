package com.app.back.domain.user;

import com.app.back.domain.model.exception.BusinessException;
import com.app.back.domain.model.user.User;
import com.app.back.domain.model.user.gateways.PasswordEncryptService;
import com.app.back.domain.model.user.gateways.UserService;
import com.app.back.domain.usecase.user.RegisterUserUseCase;
import com.app.back.domain.usecase.user.ValidateUserUseCase;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ValidateUserUseCaseTest {

	@InjectMocks
	private ValidateUserUseCase validateUserUseCase;
	@Mock
	private UserService userService;

	@BeforeEach
	public void init(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void validateEvent() {
		when(userService.findByEmail(anyString())).thenReturn(Flux.just(UserEntity.builder()
				.name("bryan")
				.email("bryan@reactivo.com")
				.password("hola456")
				.image("png")
				.role("admin")
				.google(true)
				.build()));

		validateUserUseCase.validateUser(UserEntity.builder()
						.uid("1243")
				.name("yyy")
				.email("rte@reactivo.com")
				.password("hola123")
				.image("jpg")
				.role("user")
				.google(false)
				.build())
				.as(StepVerifier::create)
				.expectComplete();
	}

	@Test
	void errorEmailNotFound(){
		when(userService.findByEmail(anyString())).thenReturn(Flux.empty());

		validateUserUseCase.validateUser(UserEntity.builder()
				.uid("1243")
				.name("yyy")
				.email("rte@reactivo.com")
				.password("hola123")
				.image("jpg")
				.role("user")
				.google(false)
				.build())
				.as(StepVerifier::create)
				.expectErrorMatches(throwable ->
						throwable  instanceof BusinessException && ((BusinessException) throwable).getCode().equals("EMAIL_NOT_EXIST"))
				.verify();
	}
}
