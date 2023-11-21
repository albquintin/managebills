package com.spring.redduck.managebills.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    @Column(name = "total_price")
    private Float totalPrice;
    @Column(name = "bill_type")
    private String billType;
    @Column(name = "iva_21_base")
    private Float iva21base;
    @Column(name = "iva_21_amount")
    private Float iva21amount;
    @Column(name = "iva_10_base")
    private Float iva10base;
    @Column(name = "iva_10_amount")
    private Float iva10amount;
    @Column(name = "iva_5_base")
    private Float iva5base;
    @Column(name = "iva_5_amount")
    private Float iva5amount;
    @Column(name = "iva_4_base")
    private Float iva4base;
    @Column(name = "iva_4_amount")
    private Float iva4amount;
    @Column(name = "iva_0_base")
    private Float iva0;
    @Column(name = "total_iva")
    private Float totalIva;
    @Column(name = "retention")
    private Float retention;
}
