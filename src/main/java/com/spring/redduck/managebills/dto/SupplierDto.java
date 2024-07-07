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
public class SupplierDto {
    private Long id;
    @NotEmpty(message = "El nombre del proveedor no puede estar vacío")
    private String name;
    private String cif;
    private Float firstQuarter;
    private Float secondQuarter;
    private Float thirdQuarter;
    private Float fourthQuarter;
    private Float accumulatedQuantity;
}
