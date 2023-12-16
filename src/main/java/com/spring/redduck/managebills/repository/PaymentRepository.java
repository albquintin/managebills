package com.spring.redduck.managebills.repository;

import com.spring.redduck.managebills.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
