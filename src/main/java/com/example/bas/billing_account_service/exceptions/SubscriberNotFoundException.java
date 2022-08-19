package com.example.bas.billing_account_service.exceptions;

public class SubscriberNotFoundException extends RuntimeException{
    public SubscriberNotFoundException(String id) {
        super("Could not find subscriber " + id);
    }
}
