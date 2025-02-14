package com.spring.redduck.managebills.service;

import com.spring.redduck.managebills.dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> findAllClients();
    ClientDto findClientById(Long clientId);
    void createClient(ClientDto clientDto);
    void updateClient(ClientDto clientDto);
    List<ClientDto> findAllOrdered();
    List<ClientDto> findAllClientsWithAccumulatedQuantity();
    List<ClientDto> findQuantitiesByYear(Long year);
    List<ClientDto> findClientsFor347Form(Long year);
}
