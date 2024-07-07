package com.spring.redduck.managebills.service.impl;

import com.spring.redduck.managebills.dto.ClientDto;
import com.spring.redduck.managebills.entity.Client;
import com.spring.redduck.managebills.mapper.ClientMapper;
import com.spring.redduck.managebills.repository.ClientRepository;
import com.spring.redduck.managebills.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    ClientRepository clientRepository;
    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
    @Override
    public List<ClientDto> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map((client) -> ClientMapper.mapToClientDto(client)).collect(Collectors.toList());
    }

    @Override
    public ClientDto findClientById(Long clientId) {
        Client client = clientRepository.findById(clientId).get();
        return ClientMapper.mapToClientDto(client);
    }

    @Override
    public void createClient(ClientDto clientDto) {
        Client client = ClientMapper.mapToClient(clientDto);
        clientRepository.save(client);
    }

    @Override
    public void updateClient(ClientDto clientDto) {
        Client client = ClientMapper.mapToClient(clientDto);
        clientRepository.save(client);
    }

    @Override
    public List<ClientDto> findAllOrdered() {
        List<Client> clients = clientRepository.findAllOrdered();
        return clients.stream().map((client) -> ClientMapper.mapToClientDto(client)).collect(Collectors.toList());
    }

    @Override
    public List<ClientDto> findAllClientsWithAccumulatedQuantity() {
        List<Client> clients = clientRepository.findAllClientsWithAccumulatedQuantity();
        return clients.stream().map((client) -> ClientMapper.mapToClientDto(client)).collect(Collectors.toList());
    }

    @Override
    public List<ClientDto> findQuantitiesByYear(Long year) {
        List<Client> clients = clientRepository.findQuantitiesByYear(year);
        return clients.stream().map((client) -> ClientMapper.mapToClientDto(client)).collect(Collectors.toList());
    }

    @Override
    public List<ClientDto> findClientsFor347Form() {
        List<Client> clients = clientRepository.findClientsFor347Form();
        return clients.stream().map((client) -> ClientMapper.mapToClientDto(client)).collect(Collectors.toList());
    }
}
