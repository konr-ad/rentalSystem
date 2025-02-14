package com.ekdev.rental_system.service;

import com.ekdev.rental_system.dto.ClientDtoRequest;
import com.ekdev.rental_system.model.Client;
import com.ekdev.rental_system.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<Client> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    public Client createClient(ClientDtoRequest clientDtoRequest) {
        if(clientRepository.findByEmail(clientDtoRequest.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Client already exists");
        }
        Client client = new Client();
        client.setEmail(clientDtoRequest.email());
        client.setFirstName(clientDtoRequest.firstName());
        client.setLastName(clientDtoRequest.lastName());
        client.setPhoneNumber(clientDtoRequest.phoneNumber());
        client.setPassword(clientDtoRequest.password());
        return clientRepository.save(client);
    }

    public void deleteById(Long id) {
        Client client = getClientById(id);
        clientRepository.delete(client);
    }

    public Client updateClient(Long id, ClientDtoRequest clientDtoRequest) {
        Client existingClient = getClientById(id);
        existingClient.setEmail(clientDtoRequest.email());
        existingClient.setFirstName(clientDtoRequest.firstName());
        existingClient.setLastName(clientDtoRequest.lastName());
        existingClient.setPhoneNumber(clientDtoRequest.phoneNumber());
        return clientRepository.save(existingClient);
    }
}
