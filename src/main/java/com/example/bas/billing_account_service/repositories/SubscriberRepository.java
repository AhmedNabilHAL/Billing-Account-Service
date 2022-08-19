package com.example.bas.billing_account_service.repositories;

import com.example.bas.billing_account_service.models.Subscriber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * A subscriber repository which gives us access to different CRUD operations
 */
@RepositoryRestResource(exported = false)
public interface SubscriberRepository extends MongoRepository<Subscriber, String> {

    /**
     * searches for a subscriber with a certain username
     * @param username unique username for subscriber
     * @return subscriber with the given username
     */
    @Query("{username:'?0'}")
    Optional<Subscriber> findItemByUsername(String username);

    /**
     * searches for a subscriber with a certain username
     * @param email unique email for subscriber
     * @return subscriber with the given email
     */
    @Query("{email:'?0'}")
    Optional<Subscriber> findItemByEmail(String email);

    /**
     * searches for all subscribers with a certain billing account number
     * @param ban billing account number
     * @return a list of subscribers
     */
    @Query("{ban:'?0'}")
    List<Subscriber> findItemsByBan(String ban);
}
