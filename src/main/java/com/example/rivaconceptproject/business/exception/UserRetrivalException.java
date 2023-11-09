package com.example.rivaconceptproject.business.exception;

public class UserRetrivalException extends RuntimeException {
    public UserRetrivalException (String errorCode){
        super(errorCode);
    }
}
