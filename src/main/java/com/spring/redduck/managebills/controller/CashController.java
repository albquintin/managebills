package com.spring.redduck.managebills.controller;

import com.spring.redduck.managebills.dto.*;
import com.spring.redduck.managebills.service.CashService;
import com.spring.redduck.managebills.utils.Utils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CashController {
    private CashService cashService;

    public CashController(CashService cashService) {
        this.cashService = cashService;
    }
    @GetMapping("/cash/cash")
    public String cash(Model model){
        List<CashDto> cashList = cashService.findAllCash();
        model.addAttribute("cashList", cashList);
        return "/cash/cash";
    }
    @GetMapping("/cash/cash/newcash")
    public String newCashForm(Model model){
        CashDto cashDto = new CashDto();
        model.addAttribute("cash", cashDto);
        return "/cash/create_cash";
    }
    @PostMapping("cash/cash")
    public String createCash(@Valid @ModelAttribute("cash") CashDto cashDto,
                                BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("cash", cashDto);
            return "/cash/create_cash";
        }
        Optional<CashDto> optionalCodiasaCash = cashService.findCodiasaCashByMonth(cashDto.getPaymentDate().getLong(ChronoField.MONTH_OF_YEAR));
        if(optionalCodiasaCash.isPresent()){
            Boolean codiasaCashPresent = true;
            model.addAttribute("codiasaCashPresent", codiasaCashPresent);
            model.addAttribute("cash", cashDto);
            return "/cash/create_cash";
        }
        BigDecimal totalIvaAmount = cashDto.getIva21amount().add(cashDto.getIva10amount()).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal totalPriceCalculated = totalIvaAmount.add(cashDto.getIva21base()).add(cashDto.getIva10base()).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal totalPrice = cashDto.getTotalPrice();
        if(totalPrice.compareTo(totalPriceCalculated) != 0 &&
                totalPrice.compareTo(totalPriceCalculated.add(BigDecimal.valueOf(0.01))) != 0 &&
                totalPrice.compareTo(totalPriceCalculated.subtract(BigDecimal.valueOf(0.01))) != 0){

            Boolean priceNotMatch = true;
            model.addAttribute("priceNotMatch", priceNotMatch);
            model.addAttribute("cash", cashDto);
            return "/cash/create_cash";
        }
        cashService.createCash(cashDto);
        return "redirect:/cash/cash";
    }
    @GetMapping("/cash/cash/delete/{cashId}")
    public String deleteCash(@PathVariable("cashId") Long cashId, Model model){
        cashService.deleteCash(cashId);
        return "redirect:/cash/cash";
    }
    @GetMapping("cash/cash/edit/{cashId}")
    public String editCashForm(@PathVariable("cashId") Long cashId, Model model){
        CashDto cashDto = cashService.findCashById(cashId);
        model.addAttribute("cash", cashDto);
        return "cash/edit_cash";
    }
    @PostMapping("cash/cash/{cashId}")
    public String updateCash(@PathVariable("cashId") Long cashId,
                                @Valid @ModelAttribute("cash") CashDto cashDto,
                                BindingResult result, Model model){
        if(result.hasErrors()){
            cashDto.setId(cashId);
            model.addAttribute("cash", cashDto);
            return "cash/edit_cash";
        }
        BigDecimal totalIvaAmount = cashDto.getIva21amount().add(cashDto.getIva10amount()).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal totalPriceCalculated = totalIvaAmount.add(cashDto.getIva21base()).add(cashDto.getIva10base()).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal totalPrice = cashDto.getTotalPrice();
        if(totalPrice.compareTo(totalPriceCalculated) != 0 &&
                totalPrice.compareTo(totalPriceCalculated.add(BigDecimal.valueOf(0.01))) != 0 &&
                totalPrice.compareTo(totalPriceCalculated.subtract(BigDecimal.valueOf(0.01))) != 0){

            Boolean priceNotMatch = true;
            model.addAttribute("priceNotMatch", priceNotMatch);
            cashDto.setId(cashId);
            model.addAttribute("cash", cashDto);
            return "/cash/edit_cash";
        }
        cashDto.setId(cashId);
        cashService.updateCash(cashDto);
        return "redirect:/cash/cash";
    }
    @GetMapping("/cash/printcash")
    public String printCash(Model model){
        List<CashDto> cashList = new ArrayList<CashDto>();
        model.addAttribute("cashList", cashList);
        model.addAttribute("codiasa", new CashDto());
        model.addAttribute("sumOfMonth", new CashDto());
        model.addAttribute("searchDone", false);
        return "/cash/print_cash";
    }
    @GetMapping("/cash/search")
    public String searchCashByMonth(@RequestParam(value = "month") String month, Model model){
        List<CashDto> cashList = cashService.findCashByMonth(Long.parseLong(month));
        model.addAttribute("cashList", cashList);
        CashDto codiasaCash = new CashDto();
        Optional<CashDto> codiasaCashOptional = cashService.findCodiasaCashByMonth(Long.parseLong(month));
        if(codiasaCashOptional.isEmpty()){
            codiasaCash.setId(0L);
            codiasaCash.setIva10base(new BigDecimal(0));
            codiasaCash.setIva10amount(new BigDecimal(0));
            codiasaCash.setIva21base(new BigDecimal(0));
            codiasaCash.setIva21amount(new BigDecimal(0));
            codiasaCash.setTotalPrice(new BigDecimal(0));
        }else if(codiasaCashOptional.isPresent()){
            codiasaCash = codiasaCashOptional.get();
        }
        model.addAttribute("codiasa", codiasaCash);
        CashDto sumOfTheMonth = returnSumsOfTheMonth(cashList, codiasaCash);
        model.addAttribute("sumOfTheMonth", sumOfTheMonth);
        String monthInLetters = Utils.returnMonth(month);
        model.addAttribute("monthInLetters", monthInLetters);
        model.addAttribute("searchDone", true);
        return "/cash/print_cash";
    }
    public CashDto returnSumsOfTheMonth(List<CashDto> cashList, CashDto codiasaCash){
        CashDto cashSum = new CashDto(0L, null, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        for(CashDto cash: cashList){
            cashSum.setTotalPrice(cashSum.getTotalPrice().add(cash.getTotalPrice()));
            cashSum.setIva21base(cashSum.getIva21base().add(cash.getIva21base()));
            cashSum.setIva21amount(cashSum.getIva21amount().add(cash.getIva21amount()));
            cashSum.setIva10base(cashSum.getIva10base().add(cash.getIva10base()));
            cashSum.setIva10amount(cashSum.getIva10amount().add(cash.getIva10amount()));
        }
        cashSum.setTotalPrice(cashSum.getTotalPrice().add(codiasaCash.getTotalPrice()));
        cashSum.setIva21base(cashSum.getIva21base().add(codiasaCash.getIva21base()));
        cashSum.setIva21amount(cashSum.getIva21amount().add(codiasaCash.getIva21amount()));
        cashSum.setIva10base(cashSum.getIva10base().add(codiasaCash.getIva10base()));
        cashSum.setIva10amount(cashSum.getIva10amount().add(codiasaCash.getIva10amount()));
        return cashSum;
    }
}
