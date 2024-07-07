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
public class PaymentDto {
    private Long id;
    private String billNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;
    private Long clientId;
    private String clientName;
    private String clientCif;
    @NotNull(message = "El campo Total Pago no puede estar vacío")
    private BigDecimal totalPrice;
    private String paymentType;

    public PaymentDto(@NotNull(message = "El campo Total Pago no puede estar vacío") BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
