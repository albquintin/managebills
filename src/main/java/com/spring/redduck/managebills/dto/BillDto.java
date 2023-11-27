package com.spring.redduck.managebills.dto;

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
    private Long orderNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate billDate;
    private Long supplierId;
    private String supplierName;
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
    @NotNull(message = "El campo IVA 5% no puede estar vacío")
    private BigDecimal iva5base;
    private BigDecimal iva5amount;
    @NotNull(message = "El campo IVA 4% no puede estar vacío")
    private BigDecimal iva4base;
    private BigDecimal iva4amount;
    @NotNull(message = "El campo Exento IVA no puede estar vacío")
    private BigDecimal iva0;
    private BigDecimal totalIva;
    @NotNull(message = "El campo Retención no puede estar vacío")
    private BigDecimal retention;

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
