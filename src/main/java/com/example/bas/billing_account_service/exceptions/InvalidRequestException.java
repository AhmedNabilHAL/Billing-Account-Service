package com.example.bas.billing_account_service.exceptions;

public class InvalidRequestException extends RuntimeException{
    InvalidRequestException(String error) {
        super("Invalid request, " + error);
    }
}
