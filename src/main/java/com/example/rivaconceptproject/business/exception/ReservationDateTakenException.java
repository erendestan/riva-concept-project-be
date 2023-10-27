package com.example.rivaconceptproject.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReservationDateTakenException extends ResponseStatusException {
    public ReservationDateTakenException(){
        super(HttpStatus.BAD_REQUEST, "RESERVATION_DATE_IS_ALREADY_TAKEN");
    }
}
