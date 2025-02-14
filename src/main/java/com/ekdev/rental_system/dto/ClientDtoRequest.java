package com.ekdev.rental_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//Class with data that was sent by a frontend
public record ClientDtoRequest(
        @NotBlank @Email String email,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String phoneNumber,
        @NotBlank String password
) {
}
