package com.ekdev.rental_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

//Class with data to be exposed on a frontend
@AllArgsConstructor
@Getter
public class ClientDtoResponse extends RepresentationModel<ClientDtoResponse> {
    private final Long id;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
}
