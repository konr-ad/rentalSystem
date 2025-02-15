package com.ekdev.rental_system.assembler;

import com.ekdev.rental_system.controller.ReservationController;
import com.ekdev.rental_system.dto.ReservationDtoRequest;
import com.ekdev.rental_system.dto.ReservationDtoResponse;
import com.ekdev.rental_system.model.Reservation;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReservationDtoAssembler extends RepresentationModelAssemblerSupport<Reservation, ReservationDtoResponse> {
    public ReservationDtoAssembler() {
        super(ReservationController.class, ReservationDtoResponse.class);
    }

    @Override
    public @NonNull ReservationDtoResponse toModel(@NonNull Reservation reservation) {
        ReservationDtoResponse dto = new ReservationDtoResponse(
                reservation.getId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getCreatedAt(),
                reservation.getClient().getId(),
                reservation.getCottage().getId()
        );

        dto.add(linkTo(methodOn(ReservationController.class).getReservationById(reservation.getId())).withSelfRel());
        return dto;
    }
}

