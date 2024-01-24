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
}
