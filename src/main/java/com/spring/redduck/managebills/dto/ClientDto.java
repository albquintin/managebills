package com.spring.redduck.managebills.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long id;
    @NotEmpty(message = "El nombre del cliente no puede estar vacío")
    private String name;
    private String dni;
    private Float accumulatedQuantity;
    private Float firstQuarter;
    private Float secondQuarter;
    private Float thirdQuarter;
    private Float fourthQuarter;
    private String address;
    private String zipCode;
    private String city;
}
