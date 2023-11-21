package com.spring.redduck.managebills.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillDto {

    private Long id;
    private Long orderNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate billDate;
    private Long supplierId;
    private String supplierName;
    private String billNumber;
    private Float totalPrice;
    private String billType;
    private Float iva21base;
    private Float iva21amount;
    private Float iva10base;
    private Float iva10amount;
    private Float iva5base;
    private Float iva5amount;
    private Float iva4base;
    private Float iva4amount;
    private Float iva0;
    private Float totalIva;
    private Float retention;
}
