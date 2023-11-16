package com.example.rivaconceptproject.business.userusecases;

import com.example.rivaconceptproject.domain.user.CreateUserRequest;
import com.example.rivaconceptproject.domain.user.CreateUserResponse;

public interface CreateUserUseCase {

    CreateUserResponse createUser(CreateUserRequest request);
}
