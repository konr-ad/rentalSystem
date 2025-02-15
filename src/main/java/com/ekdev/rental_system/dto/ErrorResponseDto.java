package com.ekdev.rental_system.dto;

public record ErrorResponseDto (
        int status,
        String error,
        String message,
        String errorCode,
        String timestamp
) {
}
