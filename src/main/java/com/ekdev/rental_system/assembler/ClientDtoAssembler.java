package com.ekdev.rental_system.assembler;

import com.ekdev.rental_system.controller.ClientController;
import com.ekdev.rental_system.dto.ClientDtoResponse;
import com.ekdev.rental_system.model.Client;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Component
public class ClientDtoAssembler extends RepresentationModelAssemblerSupport<Client, ClientDtoResponse> {
    public ClientDtoAssembler() {
        super(ClientController.class, ClientDtoResponse.class);
    }

    @Override
    public @NonNull ClientDtoResponse toModel(@NonNull Client client) {
        ClientDtoResponse dto = new ClientDtoResponse(
                client.getId(),
                client.getEmail(),
                client.getFirstName(),
                client.getLastName(),
                client.getPhoneNumber()
        );

        dto.add(linkTo(methodOn(ClientController.class).getClientById(client.getId())).withSelfRel());
        return dto;
    }
}
