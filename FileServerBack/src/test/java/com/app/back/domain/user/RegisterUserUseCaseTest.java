package com.app.back.domain.user;

import com.app.back.domain.model.user.User;
import com.app.back.domain.model.user.gateways.PasswordEncryptService;
import com.app.back.domain.model.user.gateways.UserService;
import com.app.back.domain.usecase.user.RegisterUserUseCase;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class RegisterUserUseCaseTest {

	@InjectMocks
	private RegisterUserUseCase registerUserUseCase;
	@Mock
	private UserService userService;
	@Mock
	private PasswordEncryptService passwordEncryptService;

	@BeforeEach
	public void init(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void registerEvent() {
		when(passwordEncryptService.encryptPassword(anyString())).thenReturn("encrypt");
		when(userService.saveUser(any())).thenReturn(Mono.just(UserEntity.builder()
						.name("xxx")
				.email("abc@reactivo.com")
				.password("12354")
				.image("kdg")
				.role("admin")
				.google(true)
				.build()));

		registerUserUseCase.register(UserEntity.builder()
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
}
