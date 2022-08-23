package com.example.bas.billing_account_service.controllers;

import com.example.bas.billing_account_service.models.Subscriber;
import com.example.bas.billing_account_service.services.SubscriberService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/vois/internship-program/v1")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @GetMapping("/{ban}/subscribers")
    @ResponseStatus(HttpStatus.OK)
    CollectionModel<EntityModel<Subscriber>> getSubscribers(@PathVariable(value = "ban") String ban) {
        List<EntityModel<Subscriber>> subscribers = subscriberService.getSubscribersByBan(ban).stream()
                .map(subscriber -> EntityModel.of(subscriber,
                        WebMvcLinkBuilder.linkTo(methodOn(SubscriberController.class).getSubscriber(
                                ban, subscriber.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(methodOn(SubscriberController.class).getSubscribers(ban)).withRel("subscribers")))
                .collect(Collectors.toList());

        return CollectionModel.of(subscribers, WebMvcLinkBuilder.linkTo(methodOn(SubscriberController.class).getSubscribers(ban))
                .withSelfRel());
    }

    @GetMapping("/{ban}.{subscriber}")
    EntityModel<Subscriber> getSubscriber(@PathVariable(value = "ban") String ban, @PathVariable(value = "subscriber") String subscriberId) {
        Subscriber subscriber = subscriberService.getSubscriber(ban, subscriberId);
        return EntityModel.of(subscriber, //
                WebMvcLinkBuilder.linkTo(methodOn(SubscriberController.class).getSubscriber(ban, subscriberId)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(SubscriberController.class).getSubscribers(ban)).withRel("subscribers"));
    }

    @PostMapping("/subscriber")
    EntityModel<Subscriber> createSubscriber(@RequestBody Subscriber subscriber) {
        subscriber = subscriberService.createSubscriber(subscriber);
        return EntityModel.of(subscriber, //
                WebMvcLinkBuilder.linkTo(methodOn(SubscriberController.class).
                        getSubscriber(subscriber.getBan(), subscriber.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(SubscriberController.class).getSubscribers(subscriber.getBan())).withRel("subscribers"));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public String missingValue(NullPointerException ex) {
        return ex.getMessage();
    }
}
