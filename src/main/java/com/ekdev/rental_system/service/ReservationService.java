package com.ekdev.rental_system.service;

import com.ekdev.rental_system.dto.ReservationDtoRequest;
import com.ekdev.rental_system.exception.InvalidReservationPeriodException;
import com.ekdev.rental_system.exception.ReservationNotFoundException;
import com.ekdev.rental_system.model.Reservation;
import com.ekdev.rental_system.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientService clientService;
    private final CottageService cottageService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ClientService clientService, CottageService cottageService) {
        this.reservationRepository = reservationRepository;
        this.clientService = clientService;
        this.cottageService = cottageService;
    }


    public Page<Reservation> getAllReservations(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found for id: " + id));
    }

    public Reservation createReservation(ReservationDtoRequest reservationDtoRequest) {
        if (reservationDtoRequest.startDate().isAfter(reservationDtoRequest.endDate())) {
            throw new InvalidReservationPeriodException("Start date must be before end date");
        }
        Reservation reservation = new Reservation();
        reservation.setClient(clientService.getClientById(reservationDtoRequest.clientId()));
        reservation.setCottage(cottageService.getCottageById(reservationDtoRequest.cottageId()));
        reservation.setStartDate(reservationDtoRequest.startDate());
        reservation.setEndDate(reservationDtoRequest.endDate());
        return reservationRepository.save(reservation);
    }

    public void deleteById(Long id) {
        Reservation reservation = getReservationById(id);
        reservationRepository.delete(reservation);
    }

    public Reservation updateReservation(Long id, ReservationDtoRequest reservationDtoRequest) {
        if (reservationDtoRequest.startDate().isAfter(reservationDtoRequest.endDate())) {
            throw new InvalidReservationPeriodException("Start date must be before end date");
        }
        Reservation existingReservation = getReservationById(id);
        existingReservation.setEndDate(reservationDtoRequest.endDate());
        existingReservation.setStartDate(reservationDtoRequest.startDate());
        existingReservation.setCottage(cottageService.getCottageById(reservationDtoRequest.cottageId()));
        existingReservation.setClient(clientService.getClientById(reservationDtoRequest.clientId()));
        return reservationRepository.save(existingReservation);
    }
}
