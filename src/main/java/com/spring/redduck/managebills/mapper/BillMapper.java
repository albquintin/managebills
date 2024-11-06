package com.spring.redduck.managebills.mapper;

import com.spring.redduck.managebills.dto.BillDto;
import com.spring.redduck.managebills.entity.Bill;
import com.spring.redduck.managebills.entity.Supplier;

public class BillMapper {

    public static BillDto mapToBillDto(Bill bill){
        BillDto billDto = BillDto.builder()
                .id(bill.getId())
                .orderNumber(bill.getOrderNumber())
                .supplierId(bill.getSupplier().getId())
                .supplierName(bill.getSupplier().getName())
                .billNumber(bill.getBillNumber())
                .billType(bill.getBillType())
                .billDate(bill.getBillDate())
                .totalPrice(bill.getTotalPrice())
                .iva21base(bill.getIva21base())
                .iva21amount(bill.getIva21amount())
                .iva10base(bill.getIva10base())
                .iva10amount(bill.getIva10amount())
                .ivaHalf7base(bill.getIvaHalf7base())
                .ivaHalf7amount(bill.getIvaHalf7amount())
                .iva5base(bill.getIva5base())
                .iva5amount(bill.getIva5amount())
                .iva4base(bill.getIva4base())
                .iva4amount(bill.getIva4amount())
                .iva2base(bill.getIva2base())
                .iva2amount(bill.getIva2amount())
                .iva0(bill.getIva0())
                .totalIva(bill.getTotalIva())
                .retention(bill.getRetention())
                .build();
        return billDto;
    }

    public static Bill mapToBill(BillDto billDto, Supplier supplier){
        Bill bill = Bill.builder()
                .id(billDto.getId())
                .orderNumber(billDto.getOrderNumber())
                .supplier(supplier)
                .billNumber(billDto.getBillNumber())
                .billType(billDto.getBillType())
                .billDate(billDto.getBillDate())
                .totalPrice(billDto.getTotalPrice())
                .iva21base(billDto.getIva21base())
                .iva21amount(billDto.getIva21amount())
                .iva10base(billDto.getIva10base())
                .iva10amount(billDto.getIva10amount())
                .ivaHalf7base(billDto.getIvaHalf7base())
                .ivaHalf7amount(billDto.getIvaHalf7amount())
                .iva5base(billDto.getIva5base())
                .iva5amount(billDto.getIva5amount())
                .iva4base(billDto.getIva4base())
                .iva4amount(billDto.getIva4amount())
                .iva2base(billDto.getIva2base())
                .iva2amount(billDto.getIva2amount())
                .iva0(billDto.getIva0())
                .totalIva(billDto.getTotalIva())
                .retention(billDto.getRetention())
                .build();
        return bill;
    }
}
