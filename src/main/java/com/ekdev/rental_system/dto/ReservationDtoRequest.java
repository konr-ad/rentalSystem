package com.ekdev.rental_system.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservationDtoRequest(
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull Long clientId,
        @NotNull Long cottageId
) {
}
