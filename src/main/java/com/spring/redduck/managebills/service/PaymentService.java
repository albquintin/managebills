package com.spring.redduck.managebills.service;

import com.spring.redduck.managebills.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    List<PaymentDto> findAllPayments();
    void createPayment(PaymentDto paymentDto);
    PaymentDto findPaymentById(Long paymentId);
    void deletePayment(Long clientBillId);
    void updatePayment(PaymentDto paymentDto);
    List<PaymentDto> findPaymentsByClient(Long clientId);
    List<PaymentDto> findPaymentsByClientOfCurrentYear(Long clientId);
    List<PaymentDto> findPaymentsByClientByYear(Long clientId, Long year);
    List<PaymentDto> findPaymentsOfCurrentYear();
    List<PaymentDto> findOldPayments();
    List<PaymentDto> findPaymentsByMonth(Long month);
}
