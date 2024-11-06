package com.spring.redduck.managebills.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillDto {

    private Long id;
    @NotNull(message = "El campo Número de orden no puede estar vacío")
    private Long orderNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "El campo Fecha de la factura no puede estar vacío")
    private LocalDate billDate;
    private Long supplierId;
    private String supplierName;
    @NotEmpty(message = "El número de la factura no puede estar vacío")
    private String billNumber;
    @NotNull(message = "El campo Total Factura no puede estar vacío")
    private BigDecimal totalPrice;
    private String billType;
    @NotNull(message = "El campo IVA 21% no puede estar vacío")
    private BigDecimal iva21base;
    private BigDecimal iva21amount;
    @NotNull(message = "El campo IVA 10% no puede estar vacío")
    private BigDecimal iva10base;
    private BigDecimal iva10amount;
    @NotNull(message = "El campo IVA 7.5% no puede estar vacío")
    private BigDecimal ivaHalf7base;
    private BigDecimal ivaHalf7amount;
    @NotNull(message = "El campo IVA 5% no puede estar vacío")
    private BigDecimal iva5base;
    private BigDecimal iva5amount;
    @NotNull(message = "El campo IVA 4% no puede estar vacío")
    private BigDecimal iva4base;
    private BigDecimal iva4amount;
    @NotNull(message = "El campo IVA 2% no puede estar vacío")
    private BigDecimal iva2base;
    private BigDecimal iva2amount;
    @NotNull(message = "El campo Exento IVA no puede estar vacío")
    private BigDecimal iva0;
    private BigDecimal totalIva;
    @NotNull(message = "El campo Retención no puede estar vacío")
    private BigDecimal retention;

    public BillDto(BigDecimal totalPrice, BigDecimal iva21base, BigDecimal iva10base, BigDecimal ivaHalf7base, BigDecimal iva5base, BigDecimal iva4base, BigDecimal iva2base, BigDecimal iva0, BigDecimal totalIva, BigDecimal retention) {
        this.totalPrice = totalPrice;
        this.iva21base = iva21base;
        this.iva10base = iva10base;
        this.ivaHalf7base = ivaHalf7base;
        this.iva5base = iva5base;
        this.iva4base = iva4base;
        this.iva2base = iva2base;
        this.iva0 = iva0;
        this.totalIva = totalIva;
        this.retention = retention;
    }

    public BillDto(BigDecimal totalPrice, BigDecimal iva21base, BigDecimal iva10base, BigDecimal iva5base, BigDecimal iva4base, BigDecimal iva0, BigDecimal totalIva, BigDecimal retention) {
        this.totalPrice = totalPrice;
        this.iva21base = iva21base;
        this.iva10base = iva10base;
        this.iva5base = iva5base;
        this.iva4base = iva4base;
        this.iva0 = iva0;
        this.totalIva = totalIva;
        this.retention = retention;
    }

}
