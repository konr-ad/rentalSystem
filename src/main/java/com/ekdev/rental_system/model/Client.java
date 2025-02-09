package com.ekdev.rental_system.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User {

    private String preferredLanguage;

    @Override
    public String toString() {
        return super.toString() + ", preferredLanguage=" + preferredLanguage;
    }
}