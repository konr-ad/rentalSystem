package com.ekdev.rental_system.exception;

import com.ekdev.rental_system.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleReservationNotFound(ReservationNotFoundException ex) {
        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                "Reservation Not Found",
                ex.getMessage(),
                "RESERVATION_NOT_FOUND",
                LocalDateTime.now().toString()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidReservationPeriodException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidPeriod(InvalidReservationPeriodException ex) {
        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Reservation Period",
                ex.getMessage(),
                "INVALID_PERIOD",
                LocalDateTime.now().toString()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Fallback for all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGeneralException(Exception ex) {
        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                "INTERNAL_ERROR",
                LocalDateTime.now().toString()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
