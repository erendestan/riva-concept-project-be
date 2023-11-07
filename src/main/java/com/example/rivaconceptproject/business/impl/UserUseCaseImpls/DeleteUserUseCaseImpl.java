package com.example.rivaconceptproject.business.impl.UserUseCaseImpls;

import com.example.rivaconceptproject.business.UserUseCases.DeleteUserUseCase;
import com.example.rivaconceptproject.persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository userRepository;
    @Transactional
    @Override
    public void deleteUser(long userId) {
        this.userRepository.deleteById(userId);
    }
}
