package com.example.rivaconceptproject.business.impl.UserUseCaseImplTests;

import com.example.rivaconceptproject.business.impl.UserUseCaseImpls.GetUserUseCaseImpl;
import com.example.rivaconceptproject.domain.User.User;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseImplTest {
    @Mock
    UserRepository userRepositoryMock;
    @InjectMocks
    GetUserUseCaseImpl getUserUseCase;


    @BeforeEach
    void settingUp(){
//        userRepository.clear();

        UserEntity savedUser = UserEntity.builder()
                .id(1)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555666444")
                .role(Role.Customer)
                .build();
        when(userRepositoryMock.findById(1L)).thenReturn(Optional.of(savedUser));
    }

    @Test
    void getUserById_shouldGetTheUserByGivenId(){
        long userId = 1;
        Optional<User> userOptional = getUserUseCase.getUser(userId);

//        UserEntity savedUser = userRepository.findAll().get(0);
//        long userId = savedUser.getId();



        assertTrue(userOptional.isPresent());
        User user = userOptional.get();
        assertEquals("Jack", user.getFirstName());
        assertEquals("Kral", user.getLastName());
        assertEquals("jackkral@gmail.com", user.getEmail());
        assertEquals("555666444", user.getPhoneNumber());
        assertEquals(Role.Customer, user.getRole());

        verify(userRepositoryMock).findById(userId);
    }


}