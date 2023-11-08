package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.business.exception.EmailAlreadyExistsException;
import com.example.rivaconceptproject.business.impl.UserUseCaseImpls.CreateUserUseCaseImpl;
import com.example.rivaconceptproject.domain.User.CreateUserRequest;
import com.example.rivaconceptproject.domain.User.CreateUserResponse;
import com.example.rivaconceptproject.domain.User.User;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {
    @Mock
    UserRepository userRepositoryMock;
    @InjectMocks
    CreateUserUseCaseImpl createUserUseCase;
    @BeforeEach
    public void settingUp(){
        userRepositoryMock.clear();
    }
    @AfterEach
    public void tearDown(){
        userRepositoryMock.clear();
    }
    @Test
    void createUser_saves_newUserInToFakeDb(){
        CreateUserRequest request = CreateUserRequest.builder()
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555444111")
                .password("testpassword")
                .role(Role.Worker)
                .build();

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .role(request.getRole())
                .build();

        when(userRepositoryMock.save(any(UserEntity.class)))
                .thenReturn(userEntity);

        CreateUserResponse actualResult = createUserUseCase.createUser(request);

        User user = User.builder()
                .id(1L)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .build();

        CreateUserResponse expectedResult = CreateUserResponse.builder().userId(user.getId()).build();

        assertEquals(expectedResult, actualResult);

        verify(userRepositoryMock).save(any(UserEntity.class));
    }

    @Test
    void createUser_shouldThrowEmailAlreadyExistsException(){
        CreateUserRequest request = CreateUserRequest.builder()
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555444111")
                .password("testpassword")
                .role(Role.Worker)
                .build();

        when(userRepositoryMock.existsByEmail(request.getEmail()))
                .thenThrow(new EmailAlreadyExistsException());

        assertThrows(EmailAlreadyExistsException.class, () -> createUserUseCase.createUser(request));

        verify(userRepositoryMock).existsByEmail(request.getEmail());
    }

}