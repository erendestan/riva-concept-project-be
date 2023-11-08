package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.business.impl.UserUseCaseImpls.GetUsersUseCaseImpl;
import com.example.rivaconceptproject.domain.User.GetAllUsersResponse;
import com.example.rivaconceptproject.domain.User.User;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUsersUseCaseImplTest {
    @Mock
    UserRepository userRepositoryMock;
    @InjectMocks
    GetUsersUseCaseImpl getUsersUseCase;

    @Test
    void getUsers_shouldGetAllTheUsers_ShouldReturnTheExpectedSize(){
        when(userRepositoryMock.findAll()).thenReturn(
                Arrays.asList(
                        UserEntity.builder()
                                .id(1)
                                .firstName("Jack")
                                .lastName("Kral")
                                .email("jackkral@gmail.com")
                                .phoneNumber("555666444")
                                .role(Role.Customer)
                                .build(),
                        UserEntity.builder()
                                .id(2)
                                .firstName("Edward")
                                .lastName("Ox")
                                .email("edwardox@gmail.com")
                                .phoneNumber("666555111")
                                .role(Role.Worker)
                                .build()
                )
        );
        // Call the getUsers method
        GetAllUsersResponse result = getUsersUseCase.getUsers();

        // Write assertions to validate the result
        assertEquals(2, result.getUsers().size());
        assertNotNull(result);
        verify(userRepositoryMock).findAll();
    }

    @Test
    void getUsers_shouldGetAllTheUsers_ShouldMatchTheExpectedDetails() {
        // Define the expected UserEntity objects
        UserEntity expectedUser1 = UserEntity.builder()
                .id(1)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555666444")
                .role(Role.Customer)
                .build();

        UserEntity expectedUser2 = UserEntity.builder()
                .id(2)
                .firstName("Edward")
                .lastName("Ox")
                .email("edwardox@gmail.com")
                .phoneNumber("666555111")
                .role(Role.Worker)
                .build();

        // Stub the userRepositoryMock's behavior
        when(userRepositoryMock.findAll()).thenReturn(Arrays.asList(expectedUser1, expectedUser2));

        // Call the getUsers method
        GetAllUsersResponse result = getUsersUseCase.getUsers();

        User actualUser1 = User.builder()
                .id(1)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555666444")
                .role(Role.Customer)
                .build();

        User actualUser2 = User.builder()
                .id(2)
                .firstName("Edward")
                .lastName("Ox")
                .email("edwardox@gmail.com")
                .phoneNumber("666555111")
                .role(Role.Worker)
                .build();


        // Verify that the returned UserEntity objects match the expected details
        assertEquals(actualUser1, result.getUsers().get(0));
        assertEquals(actualUser2, result.getUsers().get(1));
    }

}
