package com.example.rivaconceptproject.business.impl.UserUseCaseImplTests;


import com.example.rivaconceptproject.business.exception.InvalidCredentialsException;
import com.example.rivaconceptproject.business.impl.userusecaseimpls.LoginUseCaseImpl;
import com.example.rivaconceptproject.configuration.security.token.AccessTokenEncoder;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.domain.user.LoginRequest;
import com.example.rivaconceptproject.domain.user.LoginResponse;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseImplTest {
    @Mock
    UserRepository userRepositoryMock;
    @Mock
    PasswordEncoder passwordEncoderMock;
    @Mock
    AccessTokenEncoder accessTokenEncoderMock;
    @InjectMocks
    LoginUseCaseImpl loginUseCase;

    @Test
    void shouldLoginSuccessfully() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("test@example.com", "password123");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("encodedPassword"); // Make sure to set a valid encoded password here
        userEntity.setRole(Role.CUSTOMER);

        when(userRepositoryMock.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(userEntity));
        when(passwordEncoderMock.matches(loginRequest.getPassword(), userEntity.getPassword())).thenReturn(true);
        when(accessTokenEncoderMock.encode(any())).thenReturn("dummyAccessToken");

        // Act
        LoginResponse loginResponse = loginUseCase.login(loginRequest);

        // Assert
        assertNotNull(loginResponse);
        assertNotNull(loginResponse.getAccessToken());

        // Verify that methods were called with the correct arguments
        verify(userRepositoryMock).findByEmail(loginRequest.getEmail());
        verify(passwordEncoderMock).matches(loginRequest.getPassword(), userEntity.getPassword());
        verify(accessTokenEncoderMock).encode(any());
    }

    @Test
    void shouldThrowInvalidCredentialsExceptionWhenUserNotFound() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("nonexistent@example.com", "password123");
        when(userRepositoryMock.findByEmail(loginRequest.getEmail())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(loginRequest));

        // Verify that methods were called with the correct arguments
        verify(userRepositoryMock).findByEmail(loginRequest.getEmail());
        verifyNoInteractions(passwordEncoderMock, accessTokenEncoderMock);
    }

    @Test
    void shouldThrowInvalidCredentialsExceptionWhenPasswordMismatch() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("test@example.com", "wrongPassword");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("encodedPassword"); // Make sure to set a valid encoded password here
        userEntity.setRole(Role.CUSTOMER);

        when(userRepositoryMock.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(userEntity));
        when(passwordEncoderMock.matches(loginRequest.getPassword(), userEntity.getPassword())).thenReturn(false);

        // Act and Assert
        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(loginRequest));

        // Verify that methods were called with the correct arguments
        verify(userRepositoryMock).findByEmail(loginRequest.getEmail());
        verify(passwordEncoderMock).matches(loginRequest.getPassword(), userEntity.getPassword());
        verifyNoInteractions(accessTokenEncoderMock);
    }
}
