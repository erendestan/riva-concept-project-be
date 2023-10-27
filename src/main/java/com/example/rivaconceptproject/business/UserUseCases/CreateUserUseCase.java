package com.example.rivaconceptproject.business.UserUseCases;

import com.example.rivaconceptproject.domain.User.CreateUserRequest;
import com.example.rivaconceptproject.domain.User.CreateUserResponse;

public interface CreateUserUseCase {

    CreateUserResponse createUser(CreateUserRequest request);
}
