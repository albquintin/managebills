package com.spring.redduck.managebills.service.impl;

import com.spring.redduck.managebills.dto.PaymentDto;
import com.spring.redduck.managebills.entity.Client;
import com.spring.redduck.managebills.entity.Payment;
import com.spring.redduck.managebills.mapper.PaymentMapper;
import com.spring.redduck.managebills.repository.PaymentRepository;
import com.spring.redduck.managebills.repository.ClientRepository;
import com.spring.redduck.managebills.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private ClientRepository clientRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, ClientRepository clientRepository){
        this.paymentRepository = paymentRepository;
        this.clientRepository = clientRepository;
    }
    @Override
    public List<PaymentDto> findAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map((payment) -> PaymentMapper.mapToPaymentDto(payment)).collect(Collectors.toList());
    }

    @Override
    public void createPayment(PaymentDto paymentDto) {
        Optional<Client> client = clientRepository.findById(paymentDto.getClientId());
        Payment payment = PaymentMapper.mapToPayment(paymentDto, client.get());
        paymentRepository.save(payment);
    }

    @Override
    public PaymentDto findPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).get();
        return PaymentMapper.mapToPaymentDto(payment);
    }

    @Override
    public void deletePayment(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    @Override
    public void updatePayment(PaymentDto paymentDto) {
        Optional<Client> client = clientRepository.findById(paymentDto.getClientId());
        Payment payment = PaymentMapper.mapToPayment(paymentDto, client.get());
        paymentRepository.save(payment);
    }

    @Override
    public List<PaymentDto> findPaymentsByClient(Long clientId) {
        List<Payment> payments = paymentRepository.findPaymentsByClient(clientId);
        return payments.stream().map((payment) -> PaymentMapper.mapToPaymentDto(payment)).collect(Collectors.toList());
    }

    @Override
    public List<PaymentDto> findPaymentsByClientOfCurrentYear(Long clientId) {
        List<Payment> payments = paymentRepository.findPaymentsByClientOfCurrentYear(clientId);
        return payments.stream().map((payment) -> PaymentMapper.mapToPaymentDto(payment)).collect(Collectors.toList());
    }

    @Override
    public List<PaymentDto> findPaymentsByClientByYear(Long clientId, Long year) {
        List<Payment> payments = paymentRepository.findPaymentsByClientByYear(clientId, year);
        return payments.stream().map((payment) -> PaymentMapper.mapToPaymentDto(payment)).collect(Collectors.toList());
    }

    @Override
    public List<PaymentDto> findPaymentsOfCurrentYear() {
        List<Payment> payments = paymentRepository.findPaymentsOfCurrentYear();
        return payments.stream().map((payment) -> PaymentMapper.mapToPaymentDto(payment)).collect(Collectors.toList());
    }

    @Override
    public List<PaymentDto> findOldPayments() {
        List<Payment> payments = paymentRepository.findOldPayments();
        return payments.stream().map((payment) -> PaymentMapper.mapToPaymentDto(payment)).collect(Collectors.toList());
    }

    @Override
    public List<PaymentDto> findPaymentsByMonth(Long month, Long year) {
        List<Payment> payments = paymentRepository.findPaymentsByMonth(month, year);
        return payments.stream().map((payment) -> PaymentMapper.mapToPaymentDto(payment)).collect(Collectors.toList());
    }
}
