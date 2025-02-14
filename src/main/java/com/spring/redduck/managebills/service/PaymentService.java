package com.spring.redduck.managebills.service;

import com.spring.redduck.managebills.dto.PaymentDto;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<PaymentDto> findAllPayments();
    void createPayment(PaymentDto paymentDto);
    PaymentDto findPaymentById(Long paymentId);
    void deletePayment(Long clientBillId);
    void updatePayment(PaymentDto paymentDto);
    Optional<PaymentDto> findPaymentByBillNumberAndYear(String billNumber, Long year);
    List<PaymentDto> findPaymentsByClient(Long clientId);
    List<PaymentDto> findPaymentsByClientOfCurrentYear(Long clientId);
    List<PaymentDto> findPaymentsByClientByYear(Long clientId, Long year);
    List<PaymentDto> findPaymentsOfCurrentYear();
    List<PaymentDto> findOldPayments();
    List<PaymentDto> findPaymentsByMonth(Long month, Long year);
}
