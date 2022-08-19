package com.example.bas.billing_account_service.advices;

import com.example.bas.billing_account_service.exceptions.SubscriberExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SubscriberExistsAdvice {

    @ResponseBody
    @ExceptionHandler(SubscriberExistsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String subscriberExistsHandler(SubscriberExistsException ex) {
        return ex.getMessage();
    }
}
