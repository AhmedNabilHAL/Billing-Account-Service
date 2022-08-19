package com.example.bas.billing_account_service.services;

import com.example.bas.billing_account_service.exceptions.InvalidRequestException;
import com.example.bas.billing_account_service.exceptions.SubscriberExistsException;
import com.example.bas.billing_account_service.exceptions.SubscriberNotFoundException;
import com.example.bas.billing_account_service.exceptions.UnrelatedBanException;
import com.example.bas.billing_account_service.models.Subscriber;
import com.example.bas.billing_account_service.repositories.SubscriberRepository;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * This service provides an api to interact with our subscriber model
 */
@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    private Pattern startWithAlphaPattern;

    private Pattern alphaNumericPattern;

    private Pattern emailPattern;

    public SubscriberService() {
        String startWithAlphaRe = "^[A-Za-z].*";
        startWithAlphaPattern = Pattern.compile(startWithAlphaRe);

        String alphaNumericRe = "\\w*$";
        alphaNumericPattern = Pattern.compile(alphaNumericRe);

        String emailRe = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        emailPattern = Pattern.compile(emailRe);
    }
    /**
     * verifies if the ban is valid
     * @param ban
     * @throws InvalidRequestException if ban is invalid
     */
    private void verifyBan(String ban) throws InvalidRequestException{
        if (!NumberUtils.isDigits(ban)) throw new InvalidRequestException("Billing account number can only contain numbers");
        if (ban.length() != 9) throw new InvalidRequestException("Billing account number needs to be 9 digits long");
    }

    /**
     * checks if username is valid
     * @param username
     * @throws InvalidRequestException if invalid username
     */
    private void verifyUsername(String username) throws InvalidRequestException {

        if (!startWithAlphaPattern.matcher(username).matches())
            throw new InvalidRequestException("Username has to start with an alphabet");
        if (!alphaNumericPattern.matcher(username).matches())
            throw new InvalidRequestException("Username has to only contain an alphanumeric characters");
        if (username.length() < 3 || username.length() > 15)
            throw new InvalidRequestException("Username has to be between 3 and 15 characters long");

    }

    /**
     * checks if email is valid
     * @param email
     * @throws InvalidRequestException if invalid email
     */
    private void verifyEmail(String email) throws InvalidRequestException {
        if (!emailPattern.matcher(email).matches())
            throw new InvalidRequestException("invalid email");
    }

    /**
     * checks if phone number is valid
     * @param phone
     * @throws InvalidRequestException if invalid phone number
     */
    private void verifyPhone(String phone) throws InvalidRequestException {
        if (!NumberUtils.isDigits(phone)) throw new InvalidRequestException("phone number can only contain numbers");
        if (phone.length() != 11) throw new InvalidRequestException("phone number needs to be 11 digits long");
    }

    /**
     * gets all subscribers subscribed to a given billing account
     * @param ban
     * @return list of subscribers
     */
    public List<Subscriber> getSubscribersByBan(String ban){
        verifyBan(ban);

        return subscriberRepository.findItemsByBan(ban);
    }

    /**
     * gets a subscriber with given subscriber id and ban
     * @param ban billing account number
     * @param subscriberId
     * @return subscriber with given id and ban
     * @throws SubscriberNotFoundException if no subscriber exists
     * @throws UnrelatedBanException if wrong billing account number
     */
    public Subscriber getSubscriber(String ban, String subscriberId) throws SubscriberNotFoundException, UnrelatedBanException{
        verifyBan(ban);

        Subscriber subscriber = subscriberRepository.findById(subscriberId).orElseThrow(
                ()-> new SubscriberNotFoundException(subscriberId)
        );

        if (subscriber.getBan().equalsIgnoreCase(ban)) throw new UnrelatedBanException(subscriberId, ban);

        return subscriber;
    }

    /**
     * creates new subscriber
     * @param subscriber
     * @return the created subscriber
     * @throws SubscriberExistsException if subscriber already exists
     */
    public Subscriber createSubscriber(Subscriber subscriber) throws SubscriberExistsException {
        verifyBan(subscriber.getBan());
        verifyUsername(subscriber.getUsername());
        verifyEmail(subscriber.getEmail());
        verifyPhone(subscriber.getPhone());

        if (subscriberRepository.findById(subscriber.getId()).isPresent())
            throw new SubscriberExistsException("subscriber id", subscriber.getId());

        if (subscriberRepository.findItemByUsername(subscriber.getUsername()).isPresent())
            throw new SubscriberExistsException("username", subscriber.getUsername());

        return subscriberRepository.save(subscriber);
    }
}
