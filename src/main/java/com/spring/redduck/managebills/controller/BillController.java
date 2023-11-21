package com.spring.redduck.managebills.controller;

import com.spring.redduck.managebills.dto.BillDto;
import com.spring.redduck.managebills.dto.SupplierDto;
import com.spring.redduck.managebills.entity.Supplier;
import com.spring.redduck.managebills.service.BillService;
import com.spring.redduck.managebills.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DecimalFormat;
import java.util.List;

@Controller
public class BillController {
    private BillService billService;
    private SupplierService supplierService;
    DecimalFormat df = new DecimalFormat("#.00");

    public BillController(BillService billService, SupplierService supplierService){
        this.billService = billService;
        this.supplierService = supplierService;
    }

    @GetMapping("/bills/bills")
    public String bills(Model model){
        List<BillDto> bills = billService.findAllBills();
        model.addAttribute("bills", bills);
        return "/bills/bills";
    }
    @GetMapping("/bills/printbills")
    public String printBills(Model model){
        List<BillDto> bills = billService.findAllBills();
        model.addAttribute("bills", bills);
        return "/bills/print_bills";
    }

    @GetMapping("/bills/bills/newbill")
    public String newBillForm(Model model){
        BillDto billDto = new BillDto();
        model.addAttribute("bill", billDto);
        List<SupplierDto> suppliers = supplierService.findAllSuppliers();
        model.addAttribute("suppliers", suppliers);
        return "/bills/create_bill";
    }

    @PostMapping("bills/bills")
    public String createBill(@Valid @ModelAttribute("bill") BillDto billDto, Model model){
        billDto.setIva21amount((float) Math.round(billDto.getIva21base()*21)/100);
        billDto.setIva10amount((float) Math.round(billDto.getIva10base()*10)/100);
        billDto.setIva5amount((float) Math.round(billDto.getIva5base()*5)/100);
        billDto.setIva4amount((float) Math.round(billDto.getIva4base()*4)/100);
        Float totalIvaAmount = billDto.getIva21amount() + billDto.getIva10amount() + billDto.getIva5amount() + billDto.getIva4amount();
        billDto.setTotalIva(totalIvaAmount);
        billService.createBill(billDto);
        return "redirect:/bills/bills";
    }

    @GetMapping("/bills/bills/view/{billId}")
    public String viewBill(@PathVariable("billId") Long billId, Model model){
        BillDto billDto = billService.findBillById(billId);
        model.addAttribute("bill", billDto);
        return "bills/view_bill";
    }

    @GetMapping("/bills/bills/delete/{billId}")
    public String deleteBill(@PathVariable("billId") Long billId, Model model){
        billService.deleteBill(billId);
        return "redirect:/bills/bills";
    }

    @GetMapping("bills/bills/edit/{eventId}")
    public String editBillForm(@PathVariable("eventId") Long billId, Model model){
        BillDto billDto = billService.findBillById(billId);
        model.addAttribute("bill", billDto);
        List<SupplierDto> suppliers = supplierService.findAllSuppliers();
        model.addAttribute("suppliers", suppliers);
        return "bills/edit_bill";
    }

    @PostMapping("bills/bills/{billId}")
    public String updateBill(@PathVariable("billId") Long billId,
                              @Valid @ModelAttribute("bill") BillDto billDto,
                              Model model){
        billDto.setIva21amount((float) Math.round(billDto.getIva21base()*21)/100);
        billDto.setIva10amount((float) Math.round(billDto.getIva10base()*10)/100);
        billDto.setIva5amount((float) Math.round(billDto.getIva5base()*5)/100);
        billDto.setIva4amount((float) Math.round(billDto.getIva4base()*4)/100);
        Float totalIvaAmount = billDto.getIva21amount() + billDto.getIva10amount() + billDto.getIva5amount() + billDto.getIva4amount();
        billDto.setTotalIva(totalIvaAmount);
        billDto.setId(billId);
        billService.updateBill(billDto);
        return "redirect:/bills/bills";
    }

}
