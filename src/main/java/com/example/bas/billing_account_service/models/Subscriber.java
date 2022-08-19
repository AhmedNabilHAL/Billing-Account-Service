package com.example.bas.billing_account_service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * This class is used to model a subscriber in our database
 */
@Document("subscribers")
public class Subscriber {
    @Id
    private String id;

    @Indexed
    private String username;

    private String passwordHash;

    private String firstName;

    private String lastName;

    @Indexed
    private String email;

    private String phone;

    @Indexed
    private String ban;

    public Subscriber(String username, String passwordHash, String firstName, String lastName, String email, String phone, String ban) {
        this.username = Objects.requireNonNull(username, "username is missing");
        this.passwordHash = Objects.requireNonNull(passwordHash, "passwordHash is missing");
        this.firstName = Objects.requireNonNull(firstName, "firstName is missing");
        this.lastName = Objects.requireNonNull(lastName, "lastName is missing");
        this.email = Objects.requireNonNull(email, "email is missing");
        this.phone = Objects.requireNonNull(phone, "phone is missing");
        this.ban = Objects.requireNonNull(ban, "ban is missing");
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", ban='" + ban + '\'' +
                '}';
    }
}
