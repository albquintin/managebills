package com.spring.redduck.managebills.controller;

import com.spring.redduck.managebills.dto.BillDto;
import com.spring.redduck.managebills.dto.PaymentDto;
import com.spring.redduck.managebills.dto.ClientDto;
import com.spring.redduck.managebills.dto.SupplierDto;
import com.spring.redduck.managebills.service.PaymentService;
import com.spring.redduck.managebills.service.ClientService;
import com.spring.redduck.managebills.utils.Utils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<PaymentDto> payments = paymentService.findPaymentsOfCurrentYear();
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
        List<PaymentDto> payments = paymentService.findPaymentsByClientOfCurrentYear(clientId);
        model.addAttribute("payments", payments);
        ClientDto client = clientService.findClientById(clientId);
        model.addAttribute("client", client.getName());
        model.addAttribute("clientId", clientId);
        model.addAttribute("year", "2024");
        return "clients/client_payments";
    }
    @GetMapping("/payments/oldpayments")
    public String oldPayments(Model model){
        List<PaymentDto> payments = paymentService.findOldPayments();
        model.addAttribute("payments", payments);
        return "/payments/old_payments";
    }

    @GetMapping("/client_payments/searchPaymentsByYear/{clientId}")
    public String searchPaymentsByClientAndYear(@RequestParam(value = "year") String year, @PathVariable("clientId") Long clientId, Model model){
        List<PaymentDto> payments = paymentService.findPaymentsByClientByYear(clientId, Long.parseLong(year));
        model.addAttribute("payments", payments);
        ClientDto client = clientService.findClientById(clientId);
        model.addAttribute("client", client.getName());
        model.addAttribute("year", year);
        return "clients/client_payments";
    }

    @GetMapping("/payments/printpayments")
    public String printPayments(Model model){
        List<PaymentDto> payments = new ArrayList<PaymentDto>();
        model.addAttribute("payments", payments);
        model.addAttribute("searchDone", false);
        return("/payments/print_payments");
    }

    @GetMapping("/payments/search")
    public String searchPaymentsByMonth(@RequestParam(value = "month") String month, Model model){
        List<PaymentDto> payments = paymentService.findPaymentsByMonth(Long.parseLong(month));
        model.addAttribute("payments", payments);
        String monthInLetters = Utils.returnMonth(month);
        model.addAttribute("monthInLetters", monthInLetters);
        model.addAttribute("searchDone", true);
        return("/payments/print_payments");
    }
}
