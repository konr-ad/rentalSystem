package com.ekdev.rental_system.controller;

import com.ekdev.rental_system.assembler.ReservationDtoAssembler;
import com.ekdev.rental_system.dto.ReservationDtoRequest;
import com.ekdev.rental_system.dto.ReservationDtoResponse;
import com.ekdev.rental_system.model.Reservation;
import com.ekdev.rental_system.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationDtoAssembler reservationDtoAssembler;
    private final PagedResourcesAssembler<Reservation> pagedResourcesAssembler;

    @Autowired
    public ReservationController(ReservationService reservationService, PagedResourcesAssembler<?> genericAssembler,
                                 ReservationDtoAssembler reservationDtoAssembler) {
        this.reservationService = reservationService;
        this.reservationDtoAssembler = reservationDtoAssembler;
        @SuppressWarnings("unchecked")
        PagedResourcesAssembler<Reservation> castedAssember = (PagedResourcesAssembler<Reservation>) genericAssembler;
        this.pagedResourcesAssembler = castedAssember;
    }

    @GetMapping()
    public PagedModel<ReservationDtoResponse> getAllReservations(
            @PageableDefault(size = 10, sort = "startDate", direction = Sort.Direction.DESC)
            Pageable pageable) {
        Page<Reservation> reservations = reservationService.getAllReservations(pageable);
        return pagedResourcesAssembler.toModel(reservations, reservationDtoAssembler);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDtoResponse> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        ReservationDtoResponse dto = reservationDtoAssembler.toModel(reservation);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ReservationDtoResponse> createReservation(@Valid @RequestBody ReservationDtoRequest reservationDtoRequest) {
        Reservation reservation = reservationService.createReservation(reservationDtoRequest);
        ReservationDtoResponse reservationDtoResponse = reservationDtoAssembler.toModel(reservation);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reservation.getId())
                .toUri();
        return ResponseEntity.created(location).body(reservationDtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDtoResponse> updateReservation(@PathVariable Long id,
                                                                    @Valid @RequestBody ReservationDtoRequest reservationDtoRequest) {
        Reservation reservation = reservationService.updateReservation(id, reservationDtoRequest);
        ReservationDtoResponse reservationDtoResponse = reservationDtoAssembler.toModel(reservation);
        return ResponseEntity.ok(reservationDtoResponse);
    }
}
