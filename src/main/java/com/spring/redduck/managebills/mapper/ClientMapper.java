package com.spring.redduck.managebills.mapper;

import com.spring.redduck.managebills.dto.ClientDto;
import com.spring.redduck.managebills.entity.Client;

public class ClientMapper {

    public static ClientDto mapToClientDto(Client client){
        ClientDto clientDto = ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .dni(client.getDni())
                .accumulatedQuantity(client.getAccumulatedQuantity())
                .address(client.getAddress())
                .zipCode(client.getZipCode())
                .city(client.getCity())
                .build();
        return clientDto;
    }

    public static Client mapToClient(ClientDto clientDto) {
        Client client = Client.builder()
                .id(clientDto.getId())
                .name(clientDto.getName())
                .dni(clientDto.getDni())
                .accumulatedQuantity(clientDto.getAccumulatedQuantity())
                .address(clientDto.getAddress())
                .zipCode(clientDto.getZipCode())
                .city(clientDto.getCity())
                .build();
        return client;
    }
}
