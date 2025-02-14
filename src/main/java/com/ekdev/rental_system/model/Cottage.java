package com.ekdev.rental_system.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "cottages")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cottage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(length = 2048)
    private String description;

    @Column(nullable = false)
    private BigDecimal pricePerNight;

    @Column(nullable = false)
    private String capacity;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
