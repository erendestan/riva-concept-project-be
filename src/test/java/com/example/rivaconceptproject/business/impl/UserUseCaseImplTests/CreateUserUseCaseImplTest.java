package com.example.rivaconceptproject.business.impl.UserUseCaseImplTests;

import com.example.rivaconceptproject.business.exception.EmailAlreadyExistsException;
import com.example.rivaconceptproject.business.impl.userusecaseimpls.CreateUserUseCaseImpl;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.domain.user.CreateUserRequest;
import com.example.rivaconceptproject.domain.user.CreateUserResponse;
import com.example.rivaconceptproject.domain.user.User;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {
    @Mock
    UserRepository userRepositoryMock;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    CreateUserUseCaseImpl createUserUseCase;


    @Test
    void createUser_saves_newUserInToFakeDb(){

        CreateUserRequest request = CreateUserRequest.builder()
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555444111")
                .password("testpassword")
                .role(Role.WORKER)
                .isActive(true)
                .build();

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .role(request.getRole())
                .isActive(request.isActive())
                .build();

        when(userRepositoryMock.save(any(UserEntity.class)))
                .thenReturn(userEntity);

        when(passwordEncoder.encode(any(CharSequence.class)))
                .thenReturn("encodedPassword");


        CreateUserResponse actualResult = createUserUseCase.createUser(request);

        User user = User.builder()
                .id(1L)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .isActive(request.isActive())
                .build();

        CreateUserResponse expectedResult = CreateUserResponse.builder().userId(user.getId()).build();

        assertEquals(expectedResult, actualResult);

        verify(userRepositoryMock).save(any(UserEntity.class));
        verify(passwordEncoder).encode("testpassword");
    }

    @Test
    void createUser_shouldThrowEmailAlreadyExistsException(){
        CreateUserRequest request = CreateUserRequest.builder()
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555444111")
                .password("testpassword")
                .role(Role.WORKER)
                .isActive(true)
                .build();

        when(userRepositoryMock.existsByEmail(request.getEmail()))
                .thenThrow(new EmailAlreadyExistsException());

        assertThrows(EmailAlreadyExistsException.class, () -> createUserUseCase.createUser(request));

        verify(userRepositoryMock).existsByEmail(request.getEmail());
    }


    @Test
    void createUserWithEmptyFields_Should_Throw_Illegal_Argument_Exception() {
        // Empty Mail
        CreateUserRequest request = CreateUserRequest.builder()
                .firstName("Jack")
                .lastName("Kral")
                .phoneNumber("555444111")
                .password("testpassword")
                .role(Role.WORKER)
                .isActive(true)
                .build();

        // The createUser method should throw an exception if any of the fields are empty
        assertThrows(IllegalArgumentException.class, () -> createUserUseCase.createUser(request));
    }
}