package com.example.rivaconceptproject.business.impl.UserUseCaseImplTests;

import com.example.rivaconceptproject.business.exception.UserNotFoundException;
import com.example.rivaconceptproject.business.impl.UserUseCaseImpls.GetUserUseCaseImpl;
import com.example.rivaconceptproject.domain.user.User;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseImplTest {
    @Mock
    UserRepository userRepositoryMock;
    @InjectMocks
    GetUserUseCaseImpl getUserUseCase;


    @BeforeEach
    void setUp() {
        UserEntity savedUser = UserEntity.builder()
                .id(1L)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555666444")
                .role(Role.CUSTOMER)
                .build();
        lenient().when(userRepositoryMock.findById(1L)).thenReturn(Optional.of(savedUser));
    }

    @Test
    void shouldRetrieveUserById() {
        long userId = 1;
        Optional<User> userOptional = getUserUseCase.getUser(userId);


        assertTrue(userOptional.isPresent());
        User user = userOptional.get();
        assertEquals("Jack", user.getFirstName());
        assertEquals("Kral", user.getLastName());
        assertEquals("jackkral@gmail.com", user.getEmail());
        assertEquals("555666444", user.getPhoneNumber());
        assertEquals(Role.CUSTOMER, user.getRole());

        // Verify that the UserRepository's findById method was called with the correct userId
        verify(userRepositoryMock).findById(userId);
    }


    @Test
    void shouldThrowUserNotFoundExceptionWhenUserIdNotFound() {
        long userId = 2;

        // Set up the behavior first
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.empty());

        // Invoke the method that triggers the behavior
        assertThrows(UserNotFoundException.class, () -> getUserUseCase.getUser(userId));

        // Verify that the UserRepository's findById method was called with the correct userId
        verify(userRepositoryMock).findById(userId);
    }

}