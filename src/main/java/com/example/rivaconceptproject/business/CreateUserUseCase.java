package com.example.rivaconceptproject.business;

import com.example.rivaconceptproject.domain.CreateUserRequest;
import com.example.rivaconceptproject.domain.CreateUserResponse;

public interface CreateUserUseCase {

    CreateUserResponse createUser(CreateUserRequest request);
}
