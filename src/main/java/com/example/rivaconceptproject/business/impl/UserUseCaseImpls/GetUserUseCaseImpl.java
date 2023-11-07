package com.example.rivaconceptproject.business.impl.UserUseCaseImpls;

import com.example.rivaconceptproject.business.UserUseCases.GetUserUseCase;
import com.example.rivaconceptproject.domain.User.User;
import com.example.rivaconceptproject.persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private UserRepository userRepository;

    @Transactional
    @Override
    public Optional<User> getUser(long userId) {
        return userRepository.findUserById(userId).map(UserConverter::convert);
    }
}
