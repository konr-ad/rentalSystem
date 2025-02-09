package com.ekdev.rental_system.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    private String role;

    @Override
    public String toString() {
        return super.toString() + ", role=" + role;
    }
}
