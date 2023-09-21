package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.business.GetUsersUseCase;
import com.example.rivaconceptproject.domain.GetAllUsersResponse;
import com.example.rivaconceptproject.domain.User;
import com.example.rivaconceptproject.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {
    private final UserRepository userRepository;

    @Override
    public GetAllUsersResponse getUsers() {
        List<User> users = userRepository.findAllUsers()
                .stream()
                .map(UserConverter::convert)
                .toList();

        return GetAllUsersResponse.builder()
                .users(users)
                .build();
    }
}
