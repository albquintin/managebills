package com.spring.redduck.managebills.mapper;

import com.spring.redduck.managebills.dto.CashDto;
import com.spring.redduck.managebills.entity.Cash;

public class CashMapper {
    public static CashDto mapToCashDto(Cash cash){
        CashDto cashDto = CashDto.builder()
                .id(cash.getId())
                .paymentDate(cash.getPaymentDate())
                .iva10base(cash.getIva10base())
                .iva10amount(cash.getIva10amount())
                .iva21base(cash.getIva21base())
                .iva21amount(cash.getIva21amount())
                .totalPrice(cash.getTotalPrice())
                .codiasa(cash.getCodiasa())
                .cashMoney(cash.getCashMoney())
                .creditCard(cash.getCreditCard())
                .creditPayment(cash.getCreditPayment())
                .build();
        return cashDto;
    }

    public static Cash mapToCash(CashDto cashDto){
        Cash cash = Cash.builder()
                .id(cashDto.getId())
                .paymentDate(cashDto.getPaymentDate())
                .iva10base(cashDto.getIva10base())
                .iva10amount(cashDto.getIva10amount())
                .iva21base(cashDto.getIva21base())
                .iva21amount(cashDto.getIva21amount())
                .totalPrice(cashDto.getTotalPrice())
                .codiasa(cashDto.getCodiasa())
                .cashMoney(cashDto.getCashMoney())
                .creditCard(cashDto.getCreditCard())
                .creditPayment(cashDto.getCreditPayment())
                .build();
        return cash;
    }
}
