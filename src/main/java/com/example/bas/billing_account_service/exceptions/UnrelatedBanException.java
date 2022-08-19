package com.example.bas.billing_account_service.exceptions;

public class UnrelatedBanException extends RuntimeException {
    UnrelatedBanException(String subscriberId, String ban) {
        super("Unrelated billing account number " + ban + " with subscriber with id " + subscriberId);
    }
}
