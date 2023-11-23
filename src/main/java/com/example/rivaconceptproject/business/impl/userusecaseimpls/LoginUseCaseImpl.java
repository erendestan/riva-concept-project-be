package com.example.rivaconceptproject.business.impl.userusecaseimpls;

import com.example.rivaconceptproject.business.exception.InvalidCredentialsException;
import com.example.rivaconceptproject.business.userusecases.LoginUseCase;
import com.example.rivaconceptproject.configuration.security.token.AccessTokenEncoder;
import com.example.rivaconceptproject.configuration.security.token.impl.AccessTokenImpl;
import com.example.rivaconceptproject.domain.user.LoginRequest;
import com.example.rivaconceptproject.domain.user.LoginResponse;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<UserEntity> user = userRepository.findByEmail(loginRequest.getEmail());

        if(user.isEmpty()){
            throw new InvalidCredentialsException();
        }

        if(!matchesPassword(loginRequest.getPassword(), user.get().getPassword())){
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();

    }

    private boolean matchesPassword(String rawPassword, String encodedPassword){
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(Optional<UserEntity> user){
        Long userId = user.get().getId();
        String role = user.get().getRole().toString();

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.get().getEmail(), userId, role));
    }
}
