package com.ekdev.rental_system.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "cottages")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cottage extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String number;

    @Column(length = 2048)
    private String description;

    @Column(nullable = false)
    private BigDecimal pricePerNight;

    @Column(nullable = false)
    private String capacity;

    @OneToMany(mappedBy = "cottage", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Reservation> reservations;

}
