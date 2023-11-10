package com.example.rivaconceptproject.business.exception;

public class ReservationRetrivalException extends RuntimeException {
    public ReservationRetrivalException (String errorCode){
        super(errorCode);
    }
}
