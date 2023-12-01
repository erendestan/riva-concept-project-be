package com.example.rivaconceptproject.business.impl.UserUseCaseImplTests;

import com.example.rivaconceptproject.business.impl.userusecaseimpls.UpdateUserUseCaseImpl;
import com.example.rivaconceptproject.domain.user.UpdateUserRequest;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseImplTest {
    @Mock
    UserRepository userRepositoryMock;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    UpdateUserUseCaseImpl updateUserUserCase;

    @BeforeEach
    void settingUp(){
//        userRepository.clear();
        UserEntity savedUser = UserEntity.builder()
                .id(2L)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555666444")
                .role(Role.CUSTOMER)
                .build();
        when(userRepositoryMock.findById(2L)).thenReturn(Optional.of(savedUser));
    }

    @Test
    void updateProduct_shouldUpdateTheUserByGivenId(){
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setId(2L);
        updateUserRequest.setFirstName("Rafael");
        updateUserRequest.setLastName("Ramirez");
        updateUserRequest.setEmail("rafaelramirez@gmail.com");
        updateUserRequest.setPhoneNumber("111222333");
        updateUserRequest.setRole(Role.WORKER);

        Optional<UserEntity> updatedUser = userRepositoryMock.findById(2L);
        updateUserUserCase.updateUser(updateUserRequest);
        UserEntity actualUser = updatedUser.get();

        String expectedFirstName = "Rafael";
        String expectedLastName = "Ramirez";
        String expectedEmail = "rafaelramirez@gmail.com";
        String expectedPhoneNumber = "111222333";
        Role expectedRole = Role.WORKER;

        assertEquals(expectedFirstName, actualUser.getFirstName());
        assertEquals(expectedLastName, actualUser.getLastName());
        assertEquals(expectedEmail, actualUser.getEmail());
        assertEquals(expectedPhoneNumber, actualUser.getPhoneNumber());
        assertEquals(expectedRole, actualUser.getRole());

        assertNotNull(updatedUser);

        verify(userRepositoryMock).save(actualUser);
    }
}