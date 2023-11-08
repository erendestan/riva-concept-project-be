package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.business.impl.UserUseCaseImpls.DeleteUserUseCaseImpl;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseImplTest {
    @Mock
    UserRepository userRepositoryMock;
    @InjectMocks
    DeleteUserUseCaseImpl deleteUserUseCase;

    @Test
    void deleteUser_deletesByTheUserId(){
        userRepositoryMock.save(UserEntity.builder()
                .id(1)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555666444")
                .role(Role.Customer)
                .build());
        userRepositoryMock.save(UserEntity.builder()
                .id(2)
                .firstName("Edward")
                .lastName("Ox")
                .email("edwardox@gmail.com")
                .phoneNumber("666555111")
                .role(Role.Worker)
                .build());


        deleteUserUseCase.deleteUser(2);
        verify(userRepositoryMock).deleteById(2);
        assertFalse(userRepositoryMock.findById(2).isPresent());

//        assertEquals(1, userRepositoryMock.findAll().size());
    }

}