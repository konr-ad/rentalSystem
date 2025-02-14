package com.ekdev.rental_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

//Class with data that was sent by a frontend
public record CottageDtoRequest(
        @NotBlank String number,
        @NotBlank String description,
        @NotBlank BigDecimal pricePerNight,
        @NotBlank String capacity
) {
}
