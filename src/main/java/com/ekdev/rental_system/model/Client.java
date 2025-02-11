package com.ekdev.rental_system.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("CLIENT")
public class Client extends User {

    private String phoneNumber;

    @Override
    public String toString() {
        return super.toString() + ", phoneNumber=" + phoneNumber;
    }
}