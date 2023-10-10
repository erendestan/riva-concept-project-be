package com.example.rivaconceptproject.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IdAlreadyExistsException extends ResponseStatusException {
    public IdAlreadyExistsException() {

        super(HttpStatus.BAD_REQUEST, "USER_ALREADY_EXISTS");
    }
}
