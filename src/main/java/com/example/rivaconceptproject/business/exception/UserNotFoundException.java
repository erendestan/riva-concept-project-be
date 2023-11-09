package com.example.rivaconceptproject.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException(String errorCode){
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
