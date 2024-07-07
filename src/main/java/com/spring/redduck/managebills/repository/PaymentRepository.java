package com.spring.redduck.managebills.repository;

import com.spring.redduck.managebills.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT p FROM Payment p WHERE p.client.id = :clientId")
    List<Payment> findPaymentsByClient(Long clientId);
    @Query("SELECT p FROM Payment p WHERE p.client.id = :clientId AND YEAR(p.paymentDate) = YEAR(CURDATE())")
    List<Payment> findPaymentsByClientOfCurrentYear(Long clientId);
    @Query("SELECT p FROM Payment p WHERE p.client.id = :clientId AND YEAR(p.paymentDate) = :year")
    List<Payment> findPaymentsByClientByYear(Long clientId, Long year);
    @Query("SELECT p FROM Payment p WHERE p.billNumber = :billNumber")
    Payment findPaymentByBillNumber(String billNumber);
    @Query("SELECT p FROM Payment p WHERE YEAR(p.paymentDate) = YEAR(CURDATE())")
    List<Payment> findPaymentsOfCurrentYear();
    @Query("SELECT p FROM Payment p WHERE YEAR(p.paymentDate) < YEAR(CURDATE())")
    List<Payment> findOldPayments();
    @Query("SELECT p FROM Payment p WHERE MONTH(p.paymentDate) = :month AND YEAR(p.paymentDate) = :year ORDER BY p.paymentDate")
    List<Payment> findPaymentsByMonth(Long month, Long year);
}
