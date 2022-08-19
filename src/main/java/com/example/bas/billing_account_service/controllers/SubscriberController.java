package com.example.bas.billing_account_service.controllers;

import com.example.bas.billing_account_service.models.Subscriber;
import com.example.bas.billing_account_service.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/vois/internship-program/v1")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @GetMapping("/{ban}/subscribers")
    @ResponseStatus(HttpStatus.OK)
    List<Subscriber> getSubscribers(@PathVariable(value = "ban") String ban) {
        return subscriberService.getSubscribersByBan(ban);
    }

    @GetMapping("/{ban}.{subscriber}")
    Subscriber getSubscriber(@PathVariable(value = "ban") String ban, @PathVariable(value = "subscriber") String subscriberId) {

        return subscriberService.getSubscriber(ban, subscriberId);
    }

    @PostMapping("/subscriber")
    Subscriber createSubscriber(@RequestBody Subscriber subscriber) {
        return subscriberService.createSubscriber(subscriber);
    }

}
