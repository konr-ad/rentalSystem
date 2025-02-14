package com.ekdev.rental_system.service;

import com.ekdev.rental_system.dto.CottageDtoRequest;
import com.ekdev.rental_system.model.Cottage;
import com.ekdev.rental_system.repository.CottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CottageService {

    private final CottageRepository cottageRepository;

    @Autowired
    public CottageService(CottageRepository cottageRepository) {
        this.cottageRepository = cottageRepository;
    }


    public Page<Cottage> getAllCottages(Pageable pageable) {
        return cottageRepository.findAll(pageable);
    }

    public Cottage getCottageById(Long id) {
        return cottageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cottage not found"));
    }

    public Cottage getCottageByNumber(String number) {
        return cottageRepository.findByNumber(number)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cottage not found"));
    }

    public Cottage createCottage(CottageDtoRequest cottageDtoRequest) {
        if(cottageRepository.findByNumber(cottageDtoRequest.number()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cottage already exists");
        }
        Cottage cottage = new Cottage();
        cottage.setNumber(cottageDtoRequest.number());
        cottage.setDescription(cottageDtoRequest.description());
        cottage.setCapacity(cottageDtoRequest.capacity());
        cottage.setPricePerNight(cottageDtoRequest.pricePerNight());
        return cottageRepository.save(cottage);
    }

    public void deleteById(Long id) {
        Cottage cottage = getCottageById(id);
        cottageRepository.delete(cottage);
    }

    public Cottage updateCottage(Long id, CottageDtoRequest cottageDtoRequest) {
        Cottage cottage = getCottageById(id);
        cottage.setNumber(cottageDtoRequest.number());
        cottage.setDescription(cottageDtoRequest.description());
        cottage.setPricePerNight(cottageDtoRequest.pricePerNight());
        cottage.setCapacity(cottageDtoRequest.capacity());
        return cottageRepository.save(cottage);
    }
}
