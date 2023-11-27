package com.spring.redduck.managebills.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_number")
    private Long orderNumber;
    @Column(name = "bill_date")
    private LocalDate billDate;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @Column(name = "bill_number")
    private String billNumber;
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
    @Column(name = "bill_type")
    private String billType;
    @Column(name = "iva_21_base", nullable = false)
    private BigDecimal iva21base;
    @Column(name = "iva_21_amount")
    private BigDecimal iva21amount;
    @Column(name = "iva_10_base", nullable = false)
    private BigDecimal iva10base;
    @Column(name = "iva_10_amount")
    private BigDecimal iva10amount;
    @Column(name = "iva_5_base", nullable = false)
    private BigDecimal iva5base;
    @Column(name = "iva_5_amount")
    private BigDecimal iva5amount;
    @Column(name = "iva_4_base", nullable = false)
    private BigDecimal iva4base;
    @Column(name = "iva_4_amount")
    private BigDecimal iva4amount;
    @Column(name = "iva_0_base", nullable = false)
    private BigDecimal iva0;
    @Column(name = "total_iva")
    private BigDecimal totalIva;
    @Column(name = "retention", nullable = false)
    private BigDecimal retention;
}
