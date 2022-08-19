package com.example.bas.billing_account_service.repositories;

import com.example.bas.billing_account_service.models.Subscriber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * A subscriber repository which gives us access to different CRUD operations
 */
@RepositoryRestResource(exported = false)
public interface SubscriberRepository extends MongoRepository<Subscriber, String> {
    @Query("{username:'?0'}")
    Subscriber findItemByUsername(String username);

    @Query("{email:'?0'}")
    Subscriber findItemByEmail(String email);

    @Query("{ban:'?0'}")
    List<Subscriber> findItemsByBan(String ban);
}
