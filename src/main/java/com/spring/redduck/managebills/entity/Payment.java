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
@Table(name= "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bill_number")
    private String billNumber;
    @Column(name = "payment_date")
    private LocalDate paymentDate;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
    @Column(name = "payment_type")
    private String paymentType;
}
