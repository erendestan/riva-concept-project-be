package com.example.rivaconceptproject.business.impl.UserUseCaseImpls;

import com.example.rivaconceptproject.business.UserUseCases.DeleteUserUseCase;
import com.example.rivaconceptproject.business.exception.UserNotFoundException;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository userRepository;
    @Transactional
    @Override
    public void deleteUser(Long userId) {

        Optional<UserEntity> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            // Handle the case where the user doesn't exist, e.g., throw an exception or log a message
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
    }
}
