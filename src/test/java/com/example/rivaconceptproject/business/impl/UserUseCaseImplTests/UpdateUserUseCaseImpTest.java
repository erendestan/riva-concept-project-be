package com.example.rivaconceptproject.business.impl.UserUseCaseImplTests;

import com.example.rivaconceptproject.business.impl.UserUseCaseImpls.UpdateUserUseCaseImp;
import com.example.rivaconceptproject.domain.User.UpdateUserRequest;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseImpTest {
    @Mock
    UserRepository userRepositoryMock;
    @InjectMocks
    UpdateUserUseCaseImp updateUserUserCase;

    @BeforeEach
    void settingUp(){
//        userRepository.clear();
        UserEntity savedUser = UserEntity.builder()
                .id(2)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555666444")
                .role(Role.Customer)
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
        updateUserRequest.setRole(Role.Worker);

        Optional<UserEntity> updatedUser = userRepositoryMock.findById(2L);
        updateUserUserCase.updateUser(updateUserRequest);
        UserEntity actualUser = updatedUser.get();

        String expectedFirstName = "Rafael";
        String expectedLastName = "Ramirez";
        String expectedEmail = "rafaelramirez@gmail.com";
        String expectedPhoneNumber = "111222333";
        Role expectedRole = Role.Worker;

        assertEquals(expectedFirstName, actualUser.getFirstName());
        assertEquals(expectedLastName, actualUser.getLastName());
        assertEquals(expectedEmail, actualUser.getEmail());
        assertEquals(expectedPhoneNumber, actualUser.getPhoneNumber());
        assertEquals(expectedRole, actualUser.getRole());

        assertNotNull(updatedUser);

        verify(userRepositoryMock).save(actualUser);
    }
}