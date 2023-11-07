package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.business.UserUseCases.GetUsersUseCase;
import com.example.rivaconceptproject.domain.User.GetAllUsersResponse;
import com.example.rivaconceptproject.domain.User.User;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetUsersUseCaseImplTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    GetUsersUseCase getUsersUseCase;

    @BeforeEach
    void settingUp(){
//        userRepository.clear();
//        userRepository = new FakeUserRepositoryImpl();

        userRepository.save(UserEntity.builder()
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555666444")
                .role(Role.Customer)
                .build());
        userRepository.save(UserEntity.builder()
                .firstName("Edward")
                .lastName("Ox")
                .email("edwardox@gmail.com")
                .phoneNumber("666555111")
                .role(Role.Worker)
                .build());
    }
//    @AfterEach
//    public void tearDown(){
//        userRepository.clear();
//    }

    @Test
    void getProducts_shouldGetAllTheProducts(){
//        getUsersUseCase = new GetUsersUseCaseImpl(userRepository);
        GetAllUsersResponse result = getUsersUseCase.getUsers();
        assertEquals(2, result.getUsers().size());
        assertNotNull(result);

        User expectedUser_1 = User.builder()
                .id(2)
                .firstName("Jack")
                .lastName("Kral")
                .email("jackkral@gmail.com")
                .phoneNumber("555666444")
                .role(Role.Customer)
                .build();
        User expectedUser_2 = User.builder()
                .id(3)
                .firstName("Edward")
                .lastName("Ox")
                .email("edwardox@gmail.com")
                .phoneNumber("666555111")
                .role(Role.Worker)
                .build();
//        List<User> userList = new ArrayList<>();
//        userList.add(expectedUser_1);
//        userList.add(expectedUser_2);
        assertTrue(result.getUsers().contains(expectedUser_1));
        assertTrue(result.getUsers().contains(expectedUser_2));
    }

}