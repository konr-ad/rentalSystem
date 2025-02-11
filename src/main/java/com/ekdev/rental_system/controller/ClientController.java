package com.ekdev.rental_system.controller;

import com.ekdev.rental_system.assembler.ClientDtoAssembler;
import com.ekdev.rental_system.dto.ClientDtoRequest;
import com.ekdev.rental_system.dto.ClientDtoResponse;
import com.ekdev.rental_system.model.Client;
import com.ekdev.rental_system.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientDtoAssembler clientDtoAssembler;
    private final PagedResourcesAssembler<Client> pagedResourcesAssembler;

    @Autowired
    public ClientController(ClientService clientService,
                            ClientDtoAssembler clientDtoAssembler,
                            PagedResourcesAssembler<?> genericAssembler) {
        this.clientService = clientService;
        this.clientDtoAssembler = clientDtoAssembler;
        // Cast the generic assembler to the one for Client only once here.
        @SuppressWarnings("unchecked")
        PagedResourcesAssembler<Client> castedAssembler = (PagedResourcesAssembler<Client>) genericAssembler;
        this.pagedResourcesAssembler = castedAssembler;
    }

    @GetMapping
    public PagedModel<ClientDtoResponse> getAllClients(
            @PageableDefault(size = 10, sort = "lastName") Pageable pageable) {
        Page<Client> clients = clientService.getAllClients(pageable);
        return pagedResourcesAssembler.toModel(clients, clientDtoAssembler);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDtoResponse> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        ClientDtoResponse dto = clientDtoAssembler.toModel(client);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ClientDtoResponse> createClient(@Valid @RequestBody ClientDtoRequest clientDtoRequest) {
        Client client = clientService.createClient(clientDtoRequest);
        ClientDtoResponse clientDtoResponse = clientDtoAssembler.toModel(client);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(client.getId())
                .toUri();
        return ResponseEntity.created(location).body(clientDtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient (@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDtoResponse> updateClient(@PathVariable Long id,
                                                          @Valid @RequestBody ClientDtoRequest clientDtoRequest) {
        Client client = clientService.updateClient(id, clientDtoRequest);
        ClientDtoResponse clientDtoResponse = clientDtoAssembler.toModel(client);
        return ResponseEntity.ok(clientDtoResponse);
    }
}
