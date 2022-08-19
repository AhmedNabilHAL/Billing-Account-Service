package com.example.bas.billing_account_service.advices;

import com.example.bas.billing_account_service.exceptions.SubscriberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SubscriberNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(SubscriberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String subscriberNotFoundHandler(SubscriberNotFoundException ex) {
        return ex.getMessage();
    }
}
