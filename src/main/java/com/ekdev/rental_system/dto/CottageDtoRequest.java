package com.ekdev.rental_system.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

//Class with data that was sent by a frontend
public record CottageDtoRequest(
        @NotBlank String number,
        @NotBlank String description,
        @NotNull @DecimalMin(value = "0.0", inclusive = false) BigDecimal pricePerNight,
        @NotBlank String capacity
) {
}
