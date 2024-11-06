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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        List<BillDto> bills = billService.findBillsOfCurrentYear();
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

        billDto.setIva21amount(billDto.getIva21base().multiply(BigDecimal.valueOf(0.21)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIva10amount(billDto.getIva10base().multiply(BigDecimal.valueOf(0.10)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIvaHalf7amount(billDto.getIvaHalf7base().multiply(BigDecimal.valueOf(0.075)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIva5amount(billDto.getIva5base().multiply(BigDecimal.valueOf(0.05)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIva4amount(billDto.getIva4base().multiply(BigDecimal.valueOf(0.04)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIva2amount(billDto.getIva2base().multiply(BigDecimal.valueOf(0.02)).setScale(2, RoundingMode.HALF_UP));

        BigDecimal totalIvaAmount = billDto.getIva21amount().add(billDto.getIva10amount())
                        .add(billDto.getIvaHalf7amount()).add(billDto.getIva5amount())
                        .add(billDto.getIva4amount()).add(billDto.getIva2amount())
                        .setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalPriceCalculated = totalIvaAmount.add(billDto.getIva0()).add(billDto.getIva21base())
                        .add(billDto.getIva10base()).add(billDto.getIvaHalf7base())
                        .add(billDto.getIva5base()).add(billDto.getIva4base()).add(billDto.getIva2base())
                        .setScale(2, RoundingMode.HALF_UP);
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

    @PostMapping("bills/forcedcreation")
    public String forceCreateBill(@Valid @ModelAttribute("bill") BillDto billDto,
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

        billDto.setIva21amount(billDto.getIva21base().multiply(BigDecimal.valueOf(0.21)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIva10amount(billDto.getIva10base().multiply(BigDecimal.valueOf(0.10)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIvaHalf7amount(billDto.getIvaHalf7base().multiply(BigDecimal.valueOf(0.075)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIva5amount(billDto.getIva5base().multiply(BigDecimal.valueOf(0.05)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIva4amount(billDto.getIva4base().multiply(BigDecimal.valueOf(0.04)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIva2amount(billDto.getIva2base().multiply(BigDecimal.valueOf(0.02)).setScale(2, RoundingMode.HALF_UP));

        BigDecimal totalIvaAmount = billDto.getIva21amount().add(billDto.getIva10amount())
                .add(billDto.getIvaHalf7amount()).add(billDto.getIva5amount())
                .add(billDto.getIva4amount()).add(billDto.getIva2amount())
                .setScale(2, RoundingMode.HALF_UP);

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
        billDto.setIva21amount(billDto.getIva21base().multiply(BigDecimal.valueOf(0.21)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIva10amount(billDto.getIva10base().multiply(BigDecimal.valueOf(0.10)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIvaHalf7amount(billDto.getIvaHalf7base().multiply(BigDecimal.valueOf(0.075)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIva5amount(billDto.getIva5base().multiply(BigDecimal.valueOf(0.05)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIva4amount(billDto.getIva4base().multiply(BigDecimal.valueOf(0.04)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIva2amount(billDto.getIva2base().multiply(BigDecimal.valueOf(0.02)).setScale(2, RoundingMode.HALF_UP));

        BigDecimal totalIvaAmount = billDto.getIva21amount().add(billDto.getIva10amount())
                .add(billDto.getIvaHalf7amount()).add(billDto.getIva5amount())
                .add(billDto.getIva4amount()).add(billDto.getIva2amount())
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalPriceCalculated = totalIvaAmount.add(billDto.getIva0()).add(billDto.getIva21base())
                .add(billDto.getIva10base()).add(billDto.getIvaHalf7base())
                .add(billDto.getIva5base()).add(billDto.getIva4base()).add(billDto.getIva2base())
                .setScale(2, RoundingMode.HALF_UP);
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

    @PostMapping("bills/forcedmodification/{billId}")
    public String forceUpdateBill(@PathVariable("billId") Long billId,
                             @Valid @ModelAttribute("bill") BillDto billDto,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            billDto.setId(billId);
            model.addAttribute("bill", billDto);
            List<SupplierDto> suppliers = supplierService.findAllOrdered();
            model.addAttribute("suppliers", suppliers);
            return "bills/edit_bill";
        }
        billDto.setIva21amount(billDto.getIva21base().multiply(BigDecimal.valueOf(0.21)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIva10amount(billDto.getIva10base().multiply(BigDecimal.valueOf(0.10)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIvaHalf7amount(billDto.getIvaHalf7base().multiply(BigDecimal.valueOf(0.075)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIva5amount(billDto.getIva5base().multiply(BigDecimal.valueOf(0.05)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIva4amount(billDto.getIva4base().multiply(BigDecimal.valueOf(0.04)).setScale(2, RoundingMode.HALF_UP));
        billDto.setIva2amount(billDto.getIva2base().multiply(BigDecimal.valueOf(0.02)).setScale(2, RoundingMode.HALF_UP));

        BigDecimal totalIvaAmount = billDto.getIva21amount().add(billDto.getIva10amount())
                .add(billDto.getIvaHalf7amount()).add(billDto.getIva5amount())
                .add(billDto.getIva4amount()).add(billDto.getIva2amount())
                .setScale(2, RoundingMode.HALF_UP);

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
        model.addAttribute("newTaxes", false);
        return "/bills/print_bills";
    }
    @GetMapping("/bills/search")
    public String searchBillsByMonth(@RequestParam(value = "month") String month, @RequestParam(value = "year") String year, Model model){
        int valueOfMonth =Integer.parseInt(month);
        if(valueOfMonth<10)
            month = "0"+month;
        String dateString = year + "-" + month + "-02";

        LocalDate monthOfBills = LocalDate.parse(dateString);
        LocalDate newTaxes = LocalDate.parse("2024-10-01");

        if(monthOfBills.isAfter(newTaxes)){
            model.addAttribute("newTaxes", true);
        }else{
            model.addAttribute("newTaxes", false);
        }


        List<BillDto> bills = billService.findBillsByMonth(Long.parseLong(month), Long.parseLong(year));
        model.addAttribute("bills", bills);
        String monthInLetters = Utils.returnMonth(month);
        model.addAttribute("monthInLetters", monthInLetters);
        BillDto sumOfMonth = returnSumsOfTheMonth(bills);
        model.addAttribute("sumOfMonth", sumOfMonth);
        model.addAttribute("searchDone", true);
        return "/bills/print_bills";
    }

    @GetMapping("/suppliers/supplier_bills/{supplierId}/{year}")
    public String supplierBills(@PathVariable("supplierId") Long supplierId, @PathVariable("year") Long year, Model model){
        List<BillDto> bills = billService.findBillsBySupplierAndYear(supplierId, year);
        model.addAttribute("bills", bills);
        SupplierDto supplier = supplierService.findSupplierById(supplierId);
        model.addAttribute("supplier", supplier.getName());
        return "suppliers/supplier_bills";
    }
    @GetMapping("/bills/oldbills")
    public String oldBills(Model model){
        List<BillDto> bills = billService.findOldBills();
        model.addAttribute("bills", bills);
        return "/bills/old_bills";
    }

    public BillDto returnSumsOfTheMonth(List<BillDto> bills){
        BillDto billSum = new BillDto(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0),
                BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0),
                BigDecimal.valueOf(0));
        for(BillDto bill: bills){
            billSum.setTotalPrice(billSum.getTotalPrice().add(bill.getTotalPrice()));
            billSum.setIva21base(billSum.getIva21base().add(bill.getIva21base()));
            billSum.setIva10base(billSum.getIva10base().add(bill.getIva10base()));
            billSum.setIvaHalf7base(billSum.getIvaHalf7base().add(bill.getIvaHalf7base()));
            billSum.setIva5base(billSum.getIva5base().add(bill.getIva5base()));
            billSum.setIva4base(billSum.getIva4base().add(bill.getIva4base()));
            billSum.setIva2base(billSum.getIva2base().add(bill.getIva2base()));
            billSum.setIva0(billSum.getIva0().add(bill.getIva0()));
            billSum.setTotalIva(billSum.getTotalIva().add(bill.getTotalIva()));
            billSum.setRetention(billSum.getRetention().add(bill.getRetention()));
        }
        return billSum;
    }
}
