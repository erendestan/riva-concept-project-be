package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.business.UserUseCases.CreateUserUseCase;
import com.example.rivaconceptproject.domain.User.CreateUserRequest;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.persistence.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CreateUserUseCaseImplTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CreateUserUseCase createUserUseCase;
    @BeforeEach
    public void settingUp(){
        userRepository.clear();
    }
    @AfterEach
    public void tearDown(){
        userRepository.clear();
    }
    @Test
    void createUser_saves_newUserInToFakeDb(){
//        UserRepository userRepository = new FakeUserRepositoryImpl();
//        CreateUserUseCase createUserUseCase = new CreateUserUseCaseImpl(userRepository);
        CreateUserRequest request = new CreateUserRequest("Jack","Kral","jackkral@gmail.com", 555444111, "testpassword", Role.Worker);
        createUserUseCase.createUser(request);
        assertEquals(1, userRepository.findAllUsers().size());
    }
}