package com.ekdev.rental_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ReservationDtoResponse extends RepresentationModel<ReservationDtoResponse> {
    private final Long id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalDateTime createdAt;
    private final Long clientId;
    private final Long cottageId;
}
