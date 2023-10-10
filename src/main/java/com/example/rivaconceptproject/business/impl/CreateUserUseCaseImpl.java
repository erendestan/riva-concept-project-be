package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.business.CreateUserUseCase;
import com.example.rivaconceptproject.business.exception.EmailAlreadyExistsException;
import com.example.rivaconceptproject.domain.CreateUserRequest;
import com.example.rivaconceptproject.domain.CreateUserResponse;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException();
        }

        UserEntity user = saveNewUser(request);

        return CreateUserResponse.builder()
                .userId(user.getId()).build();
    }

    private UserEntity saveNewUser(CreateUserRequest request){
        UserEntity newUser = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .role(request.getRole())
                .build();

        return userRepository.save(newUser);
    }
}
