package com.spring.redduck.managebills.controller;

import com.spring.redduck.managebills.dto.BillDto;
import com.spring.redduck.managebills.dto.PaymentDto;
import com.spring.redduck.managebills.dto.ClientDto;
import com.spring.redduck.managebills.dto.SupplierDto;
import com.spring.redduck.managebills.service.PaymentService;
import com.spring.redduck.managebills.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PaymentController {
    private PaymentService paymentService;
    private ClientService clientService;

    public PaymentController(PaymentService paymentService, ClientService clientService){
        this.paymentService = paymentService;
        this.clientService = clientService;
    }
    @GetMapping("/payments/payments")
    public String payments(Model model){
        List<PaymentDto> payments = paymentService.findAllPayments();
        model.addAttribute("payments", payments);
        return "/payments/payments";
    }
    @GetMapping("/payments/payments/newpayment")
    public String newPaymentForm(Model model){
        PaymentDto paymentDto = new PaymentDto();
        model.addAttribute("payment", paymentDto);
        List<ClientDto> clients = clientService.findAllOrdered();
        model.addAttribute("clients", clients);
        return "/payments/create_payment";
    }
    @PostMapping("payments/payments")
    public String createPayment(@Valid @ModelAttribute("payment") PaymentDto paymentDto,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("payment", paymentDto);
            List<ClientDto> clients = clientService.findAllOrdered();
            model.addAttribute("clients", clients);
            return "/payments/create_payment";
        }
        paymentService.createPayment(paymentDto);
        return "redirect:/payments/payments";
    }
    @GetMapping("/payments/payments/delete/{paymentId}")
    public String deletePayment(@PathVariable("paymentId") Long paymentId, Model model){
        paymentService.deletePayment(paymentId);
        return "redirect:/payments/payments";
    }
    @GetMapping("payments/payments/edit/{paymentId}")
    public String editPaymentForm(@PathVariable("paymentId") Long paymentId, Model model){
        PaymentDto paymentDto = paymentService.findPaymentById(paymentId);
        model.addAttribute("payment", paymentDto);
        List<ClientDto> clients = clientService.findAllOrdered();
        model.addAttribute("clients", clients);
        return "payments/edit_payment";
    }
    @PostMapping("payments/payments/{paymentId}")
    public String updatePayment(@PathVariable("paymentId") Long paymentId,
                             @Valid @ModelAttribute("payment") PaymentDto paymentDto,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            paymentDto.setId(paymentId);
            model.addAttribute("payment", paymentDto);
            List<ClientDto> clients = clientService.findAllOrdered();
            model.addAttribute("clients", clients);
            return "payments/edit_payment";
        }
        paymentDto.setId(paymentId);
        paymentService.updatePayment(paymentDto);
        return "redirect:/payments/payments";
    }
    @GetMapping("/clients/client_payments/{clientId}")
    public String clientPayments(@PathVariable("clientId") Long clientId, Model model){
        List<PaymentDto> payments = paymentService.findPaymentsByClient(clientId);
        model.addAttribute("payments", payments);
        ClientDto client = clientService.findClientById(clientId);
        model.addAttribute("client", client.getName());
        return "clients/client_payments";
    }
}
