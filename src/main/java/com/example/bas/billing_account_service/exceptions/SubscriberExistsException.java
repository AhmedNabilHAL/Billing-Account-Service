package com.example.bas.billing_account_service.exceptions;

public class SubscriberExistsException extends RuntimeException {
    public SubscriberExistsException(String duplicateField, String field) {
        super("subscriber already exists with " + duplicateField + ": " + field);
    }
}
