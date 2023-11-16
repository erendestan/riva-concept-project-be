package com.example.rivaconceptproject.business.userusecases;

import com.example.rivaconceptproject.domain.user.User;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<User> getUser(Long userId);
}
