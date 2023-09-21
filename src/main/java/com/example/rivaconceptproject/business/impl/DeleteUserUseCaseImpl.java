package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.business.DeleteUserUseCase;
import com.example.rivaconceptproject.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository userRepository;
    @Override
    public void deleteStudent(long userId) {
        this.userRepository.deleteById(userId);
    }
}
