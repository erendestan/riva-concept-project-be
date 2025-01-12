package com.example.rivaconceptproject.business.impl.userusecaseimpls;

import com.example.rivaconceptproject.business.userusecases.GetUsersUseCase;
import com.example.rivaconceptproject.business.exception.UserRetrivalException;
import com.example.rivaconceptproject.domain.user.GetAllUsersResponse;
import com.example.rivaconceptproject.domain.user.User;
import com.example.rivaconceptproject.persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public GetAllUsersResponse getUsers() {
        try {
            List<User> users = userRepository.findAll()
                    .stream()
                    .map(UserConverter::convert)
                    .toList();

            return GetAllUsersResponse.builder()
                    .users(users)
                    .build();
        } catch (Exception ex){
            throw new UserRetrivalException("Failed to retrieve users");
        }
    }
}
