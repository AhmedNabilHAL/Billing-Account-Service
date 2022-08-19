package com.example.bas.billing_account_service.services;

import com.example.bas.billing_account_service.models.Subscriber;
import com.example.bas.billing_account_service.repositories.SubscriberRepository;

import java.util.List;

/**
 * This service provides an api to interact with our subscriber model
 */
public class SubscriberService {
    private SubscriberRepository subscriberRepository;


    public List<Subscriber> getSubscribersByBan(String ban){
        if (!validBan(ban)) throw
        return subscriberRepository.findItemsByBan(ban);
    }
}
