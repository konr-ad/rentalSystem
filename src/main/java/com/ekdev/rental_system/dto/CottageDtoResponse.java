package com.ekdev.rental_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

//Class with data to be exposed on a frontend
@AllArgsConstructor
@Getter
public class CottageDtoResponse extends RepresentationModel<CottageDtoResponse> {
    private final Long id;
    private final String number;
    private final String description;
    private final BigDecimal pricePerNight;
    private final String capacity;
}
