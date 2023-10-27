package com.example.rivaconceptproject.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidReservationException extends ResponseStatusException {
    public InvalidReservationException(String errorCode){
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
