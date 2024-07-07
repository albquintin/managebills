package com.spring.redduck.managebills.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_name", nullable = false)
    private String name;
    @Column(name = "dni")
    private String dni;
    @Column(name = "accumulated_quantity")
    private Float accumulatedQuantity;
    @Column(name = "first_quarter")
    private Float firstQuarter;
    @Column(name = "second_quarter")
    private Float secondQuarter;
    @Column(name = "third_quarter")
    private Float thirdQuarter;
    @Column(name = "fourth_quarter")
    private Float fourthQuarter;
    @Column(name = "address")
    private String address;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "city")
    private String city;
}
