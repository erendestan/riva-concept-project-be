package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.business.DeleteUserUseCase;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import com.example.rivaconceptproject.persistence.impl.FakeUserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteUserUseCaseImplTest {
    UserRepository userRepository;

    @BeforeEach
    void settingUp(){
        userRepository = new FakeUserRepositoryImpl();
        userRepository.save(UserEntity.builder()
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber(555666444)
                .role(Role.Customer)
                .build());
        userRepository.save(UserEntity.builder()
                .firstName("Edward")
                .lastName("Ox")
                .email("edwardox@gmail.com")
                .phoneNumber(666555111)
                .role(Role.Worker)
                .build());
    }

    @Test
    void deleteUser_deletesByTheUserId(){
        DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCaseImpl(userRepository);
        deleteUserUseCase.deleteUser(2);
        assertFalse(userRepository.findUserById(2).isPresent());
        assertEquals(1, userRepository.findAllUsers().size());
    }

}