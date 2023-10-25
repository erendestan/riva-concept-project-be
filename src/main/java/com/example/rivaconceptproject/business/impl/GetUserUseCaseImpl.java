package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.business.GetUserUseCase;
import com.example.rivaconceptproject.domain.User.User;
import com.example.rivaconceptproject.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private UserRepository userRepository;


    @Override
    public Optional<User> getUser(long userId) {
        return userRepository.findUserById(userId).map(UserConverter::convert);
    }
}
