package com.spring.redduck.managebills.mapper;

import com.spring.redduck.managebills.dto.PaymentDto;
import com.spring.redduck.managebills.entity.Client;
import com.spring.redduck.managebills.entity.Payment;

public class PaymentMapper {
    public static PaymentDto mapToPaymentDto(Payment payment){
        PaymentDto paymentDto = PaymentDto.builder()
                .id(payment.getId())
                .billNumber(payment.getBillNumber())
                .clientId(payment.getClient().getId())
                .clientName(payment.getClient().getName())
                .clientCif(payment.getClient().getDni())
                .paymentType(payment.getPaymentType())
                .paymentDate(payment.getPaymentDate())
                .totalPrice(payment.getTotalPrice())
                .build();
        return paymentDto;
    }

    public static Payment mapToPayment(PaymentDto paymentDto, Client client){
        Payment payment = Payment.builder()
                .id(paymentDto.getId())
                .billNumber(paymentDto.getBillNumber())
                .client(client)
                .paymentType(paymentDto.getPaymentType())
                .paymentDate(paymentDto.getPaymentDate())
                .totalPrice(paymentDto.getTotalPrice())
                .build();
        return payment;
    }
}
