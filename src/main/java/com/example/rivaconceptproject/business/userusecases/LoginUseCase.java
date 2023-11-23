package com.example.rivaconceptproject.business.userusecases;

import com.example.rivaconceptproject.domain.user.LoginRequest;
import com.example.rivaconceptproject.domain.user.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
