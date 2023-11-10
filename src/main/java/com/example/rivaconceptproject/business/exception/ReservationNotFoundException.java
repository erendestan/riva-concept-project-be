package com.example.rivaconceptproject.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReservationNotFoundException extends ResponseStatusException {
    public ReservationNotFoundException(String errorCode){
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
