package com.example.rivaconceptproject.business.impl.userusecaseimpls;

import com.example.rivaconceptproject.business.exception.UnauthorizedDataAccessException;
import com.example.rivaconceptproject.business.exception.UserNotFoundException;
import com.example.rivaconceptproject.business.userusecases.GetUserUseCase;
import com.example.rivaconceptproject.configuration.security.token.AccessToken;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.domain.user.User;
import com.example.rivaconceptproject.persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private UserRepository userRepository;
    private AccessToken requestAccessToken;

    @Transactional
    @Override
    public Optional<User> getUser(Long userId) {
        if (!requestAccessToken.hasRole(Role.ADMIN.name())){
            if (requestAccessToken.getUserId() != userId){
                throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
            }
        }
        Optional<User> userOptional = userRepository.findById(userId).map(UserConverter::convert);
            if (userOptional.isEmpty()) {
                throw new UserNotFoundException("User with ID" + userId + "not found.");
            }
            return userOptional;
    }

    @Transactional
    @Override
    public Optional<User> getUserByEmail(String email) {
        Optional<User> userEmailOptional = userRepository.findByEmail(email).map(UserConverter::convert);
        if (userEmailOptional.isEmpty()){
            throw new UserNotFoundException("User credentials does not matched on the database!");
        }
        return userEmailOptional;
    }
}
