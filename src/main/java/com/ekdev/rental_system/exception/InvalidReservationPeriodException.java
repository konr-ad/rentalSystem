package com.ekdev.rental_system.exception;

public class InvalidReservationPeriodException extends RuntimeException {
    public InvalidReservationPeriodException(String message) {
        super(message);
    }
}
