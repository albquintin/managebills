package com.spring.redduck.managebills.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "supplier_name", nullable = false)
    private String name;
    @Column(name = "cif")
    private String cif;
    @Column(name = "first_quarter")
    private Float firstQuarter;
    @Column(name = "second_quarter")
    private Float secondQuarter;
    @Column(name = "third_quarter")
    private Float thirdQuarter;
    @Column(name = "fourth_quarter")
    private Float fourthQuarter;
    @Column(name = "accumulated_quantity")
    private Float accumulatedQuantity;
}
