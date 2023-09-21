package com.example.rivaconceptproject.business;

import com.example.rivaconceptproject.domain.User;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<User> getUser(long userId);
}
