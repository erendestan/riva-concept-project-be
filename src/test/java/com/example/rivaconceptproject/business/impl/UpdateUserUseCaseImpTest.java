package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.business.UpdateUserUserCase;
import com.example.rivaconceptproject.domain.UpdateUserRequest;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UpdateUserUseCaseImpTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UpdateUserUserCase updateUserUserCase;

    @BeforeEach
    void settingUp(){
        userRepository.clear();
        userRepository.save(UserEntity.builder()
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber(555666444)
                .role(Role.Customer)
                .build());
    }

    @Test
    void updateProduct_shouldUpdateTheUserByGivenId(){
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setId(2);
        updateUserRequest.setFirstName("Rafael");
        updateUserRequest.setLastName("Ramirez");
        updateUserRequest.setEmail("rafaelramirez@gmail.com");
        updateUserRequest.setPhoneNumber(111222333);
        updateUserRequest.setRole(Role.Worker);

        Optional<UserEntity> updatedUser = userRepository.findUserById(2);
        updateUserUserCase.updateUser(updateUserRequest);
        UserEntity actualUser = updatedUser.get();

        String expectedFirstName = "Rafael";
        String expectedLastName = "Ramirez";
        String expectedEmail = "rafaelramirez@gmail.com";
        long expectedPhoneNumber = 111222333;
        Role expectedRole = Role.Worker;

        assertEquals(expectedFirstName, actualUser.getFirstName());
        assertEquals(expectedLastName, actualUser.getLastName());
        assertEquals(expectedEmail, actualUser.getEmail());
        assertEquals(expectedPhoneNumber, actualUser.getPhoneNumber());
        assertEquals(expectedRole, actualUser.getRole());

        assertNotNull(updatedUser);
    }
}