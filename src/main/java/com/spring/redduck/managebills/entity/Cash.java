package com.spring.redduck.managebills.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "cash")
public class Cash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "payment_date")
    private LocalDate paymentDate;
    @Column(name = "iva_10_base", nullable = false)
    private BigDecimal iva10base;
    @Column(name = "iva_10_amount")
    private BigDecimal iva10amount;
    @Column(name = "iva_21_base", nullable = false)
    private BigDecimal iva21base;
    @Column(name = "iva_21_amount")
    private BigDecimal iva21amount;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "codiasa")
    private Boolean codiasa;
}
