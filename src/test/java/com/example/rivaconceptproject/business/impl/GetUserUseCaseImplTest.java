package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.business.GetUserUseCase;
import com.example.rivaconceptproject.domain.User;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GetUserUseCaseImplTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    GetUserUseCase getUserUseCase;


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
    void getUserById_shouldGetTheUserByGivenId(){
        UserEntity savedUser = userRepository.findAllUsers().get(0);
        long userId = savedUser.getId();

        Optional<User> userOptional = getUserUseCase.getUser(userId);

        assertTrue(userOptional.isPresent());
        User user = userOptional.get();
        assertEquals("Jack", user.getFirstName());
        assertEquals("Kral", user.getLastName());
        assertEquals("jackkral@gmail.com", user.getEmail());
        assertEquals(555666444, user.getPhoneNumber());
        assertEquals(Role.Customer, user.getRole());

    }


}