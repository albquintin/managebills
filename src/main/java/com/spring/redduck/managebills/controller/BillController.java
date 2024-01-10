package com.spring.redduck.managebills.controller;

import com.spring.redduck.managebills.dto.BillDto;
import com.spring.redduck.managebills.dto.SupplierDto;
import com.spring.redduck.managebills.service.BillService;
import com.spring.redduck.managebills.service.SupplierService;
import com.spring.redduck.managebills.utils.Utils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BillController {
    private BillService billService;
    private SupplierService supplierService;
    DecimalFormat df = new DecimalFormat("#.00");
    MathContext mc = new MathContext(3);

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

    @GetMapping("/bills/bills/newbill")
    public String newBillForm(Model model){
        BillDto billDto = new BillDto();
        model.addAttribute("bill", billDto);
        List<SupplierDto> suppliers = supplierService.findAllOrdered();
        model.addAttribute("suppliers", suppliers);
        return "/bills/create_bill";
    }

    @PostMapping("bills/bills")
    public String createBill(@Valid @ModelAttribute("bill") BillDto billDto,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("bill", billDto);
            List<SupplierDto> suppliers = supplierService.findAllOrdered();
            model.addAttribute("suppliers", suppliers);
            return "/bills/create_bill";
        }

        Optional<BillDto> billRepeatedByOrderNumber = billService.findBillByOrderNumber(billDto.getOrderNumber());
        if(billRepeatedByOrderNumber.isPresent()){
            Boolean orderNumberRepeated = true;
            model.addAttribute("orderNumberRepeated", orderNumberRepeated);
            model.addAttribute("bill", billDto);
            List<SupplierDto> suppliers = supplierService.findAllOrdered();
            model.addAttribute("suppliers", suppliers);
            return "/bills/create_bill";
        }

        Optional<BillDto> billRepeatedByBillNumber = billService.findBillByBillNumber(billDto.getBillNumber());
        if(billRepeatedByBillNumber.isPresent()){
            Boolean billNumberRepeated = true;
            model.addAttribute("billNumberRepeated", billNumberRepeated);
            model.addAttribute("bill", billDto);
            List<SupplierDto> suppliers = supplierService.findAllOrdered();
            model.addAttribute("suppliers", suppliers);
            return "/bills/create_bill";
        }

        billDto.setIva21amount(billDto.getIva21base().multiply(BigDecimal.valueOf(0.21)).setScale(2, RoundingMode.HALF_EVEN));
        billDto.setIva10amount(billDto.getIva10base().multiply(BigDecimal.valueOf(0.10)).setScale(2, RoundingMode.HALF_EVEN));
        billDto.setIva5amount(billDto.getIva5base().multiply(BigDecimal.valueOf(0.05)).setScale(2, RoundingMode.HALF_EVEN));
        billDto.setIva4amount(billDto.getIva4base().multiply(BigDecimal.valueOf(0.04)).setScale(2, RoundingMode.HALF_EVEN));

        BigDecimal totalIvaAmount = billDto.getIva21amount().add(billDto.getIva10amount()).add(billDto.getIva5amount())
                        .add(billDto.getIva4amount()).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal totalPriceCalculated = totalIvaAmount.add(billDto.getIva0()).add(billDto.getIva21base())
                        .add(billDto.getIva10base()).add(billDto.getIva5base())
                        .add(billDto.getIva4base()).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal totalPrice = billDto.getTotalPrice();

        if(totalPrice.compareTo(totalPriceCalculated) != 0 &&
            totalPrice.compareTo(totalPriceCalculated.add(BigDecimal.valueOf(0.01))) != 0 &&
            totalPrice.compareTo(totalPriceCalculated.add(BigDecimal.valueOf(0.02))) != 0 &&
            totalPrice.compareTo(totalPriceCalculated.subtract(BigDecimal.valueOf(0.01))) != 0 &&
            totalPrice.compareTo(totalPriceCalculated.subtract(BigDecimal.valueOf(0.02))) != 0){

            Boolean priceNotMatch = true;
            model.addAttribute("priceNotMatch", priceNotMatch);
            model.addAttribute("bill", billDto);
            List<SupplierDto> suppliers = supplierService.findAllOrdered();
            model.addAttribute("suppliers", suppliers);
            return "/bills/create_bill";
        }
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

    @GetMapping("bills/bills/edit/{billId}")
    public String editBillForm(@PathVariable("billId") Long billId, Model model){
        BillDto billDto = billService.findBillById(billId);
        model.addAttribute("bill", billDto);
        List<SupplierDto> suppliers = supplierService.findAllOrdered();
        model.addAttribute("suppliers", suppliers);
        return "bills/edit_bill";
    }

    @PostMapping("bills/bills/{billId}")
    public String updateBill(@PathVariable("billId") Long billId,
                              @Valid @ModelAttribute("bill") BillDto billDto,
                              BindingResult result, Model model){
        if(result.hasErrors()){
            billDto.setId(billId);
            model.addAttribute("bill", billDto);
            List<SupplierDto> suppliers = supplierService.findAllOrdered();
            model.addAttribute("suppliers", suppliers);
            return "bills/edit_bill";
        }
        billDto.setIva21amount(billDto.getIva21base().multiply(BigDecimal.valueOf(0.21)).setScale(2, RoundingMode.HALF_EVEN));
        billDto.setIva10amount(billDto.getIva10base().multiply(BigDecimal.valueOf(0.10)).setScale(2, RoundingMode.HALF_EVEN));
        billDto.setIva5amount(billDto.getIva5base().multiply(BigDecimal.valueOf(0.05)).setScale(2, RoundingMode.HALF_EVEN));
        billDto.setIva4amount(billDto.getIva4base().multiply(BigDecimal.valueOf(0.04)).setScale(2, RoundingMode.HALF_EVEN));

        BigDecimal totalIvaAmount = billDto.getIva21amount().add(billDto.getIva10amount()).add(billDto.getIva5amount())
                .add(billDto.getIva4amount()).setScale(2, RoundingMode.HALF_EVEN);

        BigDecimal totalPriceCalculated = totalIvaAmount.add(billDto.getIva0())
                .add(billDto.getIva21base()).add(billDto.getIva10base()).add(billDto.getIva5base())
                .add(billDto.getIva4base()).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal totalPrice = billDto.getTotalPrice();

        if(totalPrice.compareTo(totalPriceCalculated) != 0 &&
            totalPrice.compareTo(totalPriceCalculated.add(BigDecimal.valueOf(0.01))) != 0 &&
            totalPrice.compareTo(totalPriceCalculated.add(BigDecimal.valueOf(0.02))) != 0 &&
            totalPrice.compareTo(totalPriceCalculated.subtract(BigDecimal.valueOf(0.01))) != 0 &&
            totalPrice.compareTo(totalPriceCalculated.subtract(BigDecimal.valueOf(0.02))) != 0){
            Boolean priceNotMatch = true;
            model.addAttribute("priceNotMatch", priceNotMatch);
            billDto.setId(billId);
            model.addAttribute("bill", billDto);
            List<SupplierDto> suppliers = supplierService.findAllOrdered();
            model.addAttribute("suppliers", suppliers);
            return "/bills/edit_bill";
        }
        billDto.setTotalIva(totalIvaAmount);
        billDto.setId(billId);
        billService.updateBill(billDto);
        return "redirect:/bills/bills";
    }
    @GetMapping("/bills/printbills")
    public String printBills(Model model){
        List<BillDto> bills = new ArrayList<BillDto>();
        model.addAttribute("bills", bills);
        model.addAttribute("sumOfMonth", new BillDto());
        model.addAttribute("searchDone", false);
        return "/bills/print_bills";
    }
    @GetMapping("/bills/search")
    public String searchBillsByMonth(@RequestParam(value = "month") String month, Model model){
        List<BillDto> bills = billService.findBillsByMonth(Long.parseLong(month));
        model.addAttribute("bills", bills);
        String monthInLetters = Utils.returnMonth(month);
        model.addAttribute("monthInLetters", monthInLetters);
        BillDto sumOfMonth = returnSumsOfTheMonth(bills);
        model.addAttribute("sumOfMonth", sumOfMonth);
        model.addAttribute("searchDone", true);
        return "/bills/print_bills";
    }

    public BillDto returnSumsOfTheMonth(List<BillDto> bills){
        BillDto billSum = new BillDto(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0),
                BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        for(BillDto bill: bills){
            billSum.setTotalPrice(billSum.getTotalPrice().add(bill.getTotalPrice()));
            billSum.setIva21base(billSum.getIva21base().add(bill.getIva21base()));
            billSum.setIva10base(billSum.getIva10base().add(bill.getIva10base()));
            billSum.setIva5base(billSum.getIva5base().add(bill.getIva5base()));
            billSum.setIva4base(billSum.getIva4base().add(bill.getIva4base()));
            billSum.setIva0(billSum.getIva0().add(bill.getIva0()));
            billSum.setTotalIva(billSum.getTotalIva().add(bill.getTotalIva()));
            billSum.setRetention(billSum.getRetention().add(bill.getRetention()));
        }
        return billSum;
    }
}
