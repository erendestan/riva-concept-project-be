package com.example.rivaconceptproject.business.impl.UserUseCaseImplTests;

import com.example.rivaconceptproject.business.exception.UserNotFoundException;
import com.example.rivaconceptproject.business.impl.UserUseCaseImpls.DeleteUserUseCaseImpl;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseImplTest {
    @Mock
    UserRepository userRepositoryMock;
    @InjectMocks
    DeleteUserUseCaseImpl deleteUserUseCase;

    @Test
    void deleteUser_deletesByTheUserId() {
        // Create user entities
        UserEntity user1 = UserEntity.builder()
                .id(1L)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555666444")
                .role(Role.Customer)
                .build();

        UserEntity user2 = UserEntity.builder()
                .id(2L)
                .firstName("Edward")
                .lastName("Ox")
                .email("edwardox@gmail.com")
                .phoneNumber("666555111")
                .role(Role.Worker)
                .build();

        when(userRepositoryMock.findById(1L)).thenReturn(Optional.of(user1));
        // Mock the behavior of userRepositoryMock
        when(userRepositoryMock.findById(2L)).thenReturn(Optional.of(user2));

        // Call the deleteUser method
        deleteUserUseCase.deleteUser(2L);

        // Verify that deleteById(2L) is called
        verify(userRepositoryMock).deleteById(2L);

        // Verify that the user with ID 2 is no longer present
        when(userRepositoryMock.findById(2L)).thenReturn(Optional.empty());
        assertTrue(userRepositoryMock.findById(1L).isPresent());
        assertFalse(userRepositoryMock.findById(2L).isPresent());
    }

    @Test
    void deleteUser_userDoesNotExist() {
        // Mock the behavior of userRepositoryMock to return Optional.empty()
        when(userRepositoryMock.findById(2L)).thenReturn(Optional.empty());

        // Use assertThrows to verify that deleteUserUseCase.deleteUser(2L) throws UserNotFoundException
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> deleteUserUseCase.deleteUser(2L));

        // Verify the exception message contains the expected part
        assertTrue(exception.getMessage().contains("User with ID 2 not found"));

        // Verify that deleteById(2L) is not called
        verify(userRepositoryMock, never()).deleteById(2L);
    }

}