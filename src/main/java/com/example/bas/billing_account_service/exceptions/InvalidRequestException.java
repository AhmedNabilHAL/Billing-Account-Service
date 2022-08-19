package com.example.bas.billing_account_service.exceptions;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String error) {
        super("Invalid request, " + error);
    }
}
