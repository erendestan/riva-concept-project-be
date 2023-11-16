package com.example.rivaconceptproject.business.impl.userusecaseimpls;

import com.example.rivaconceptproject.business.userusecases.CreateUserUseCase;
import com.example.rivaconceptproject.business.exception.EmailAlreadyExistsException;
import com.example.rivaconceptproject.domain.user.CreateUserRequest;
import com.example.rivaconceptproject.domain.user.CreateUserResponse;
import com.example.rivaconceptproject.persistence.ReservationRepository;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    @Transactional
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (request == null || isAnyFieldEmpty(request)) {
            throw new IllegalArgumentException("User request contains empty fields.");
        }

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

    private boolean isAnyFieldEmpty(CreateUserRequest request) {
        return request.getFirstName() == null || request.getFirstName().isEmpty() ||
                request.getLastName() == null || request.getLastName().isEmpty() ||
                request.getEmail() == null || request.getEmail().isEmpty() ||
                request.getPhoneNumber() == null || request.getPhoneNumber().isEmpty() ||
                request.getPassword() == null || request.getPassword().isEmpty();
    }
}
