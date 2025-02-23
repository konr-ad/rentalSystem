package com.ekdev.rental_system.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity {

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    @ToString.Exclude
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cottage_id", nullable = false)
    @ToString.Exclude
    private Cottage cottage;
}
