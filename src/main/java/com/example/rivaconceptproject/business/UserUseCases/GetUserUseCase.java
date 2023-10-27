package com.example.rivaconceptproject.business.UserUseCases;

import com.example.rivaconceptproject.domain.User.User;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<User> getUser(long userId);
}
