package com.spring.redduck.managebills.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashDto {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "El campo Fecha del cobro no puede estar vacío")
    private LocalDate paymentDate;
    @NotNull(message = "El campo Base IVA 10% no puede estar vacío")
    private BigDecimal iva10base;
    @NotNull(message = "El campo Importe IVA 10% no puede estar vacío")
    private BigDecimal iva10amount;
    @NotNull(message = "El campo Base IVA 21% no puede estar vacío")
    private BigDecimal iva21base;
    @NotNull(message = "El campo Importe IVA 21% no puede estar vacío")
    private BigDecimal iva21amount;
    @NotNull(message = "El campo Bruto no puede estar vacío")
    private BigDecimal totalPrice;
}
