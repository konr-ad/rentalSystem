package com.ekdev.rental_system.assembler;

import com.ekdev.rental_system.controller.ClientController;
import com.ekdev.rental_system.controller.CottageController;
import com.ekdev.rental_system.dto.ClientDtoResponse;
import com.ekdev.rental_system.dto.CottageDtoResponse;
import com.ekdev.rental_system.model.Client;
import com.ekdev.rental_system.model.Cottage;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CottageDtoAssembler extends RepresentationModelAssemblerSupport<Cottage, CottageDtoResponse> {
    public CottageDtoAssembler() {
        super(CottageController.class, CottageDtoResponse.class);
    }

    @Override
    public @NonNull CottageDtoResponse toModel(@NonNull Cottage cottage) {
        CottageDtoResponse dto = new CottageDtoResponse(
                cottage.getId(),
                cottage.getNumber(),
                cottage.getDescription(),
                cottage.getPricePerNight(),
                cottage.getCapacity()
        );

        dto.add(linkTo(methodOn(CottageController.class).getCottageById(cottage.getId())).withSelfRel());
        return dto;
    }
}
