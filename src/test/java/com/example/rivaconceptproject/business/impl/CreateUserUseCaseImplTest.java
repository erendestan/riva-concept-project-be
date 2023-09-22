package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.business.CreateUserUseCase;
import com.example.rivaconceptproject.domain.CreateUserRequest;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.impl.FakeUserRepositoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserUseCaseImplTest {

    @Test
    void createUser_saves_newUserInToFakeDb(){
        UserRepository userRepository = new FakeUserRepositoryImpl();
        CreateUserUseCase createUserUseCase = new CreateUserUseCaseImpl(userRepository);
        CreateUserRequest request = new CreateUserRequest("Jack","Kral","jackkral@gmail.com", 555444111, Role.Worker);
        createUserUseCase.createUser(request);
        assertEquals(1, userRepository.findAllUsers().size());
    }
}