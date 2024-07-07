package com.spring.redduck.managebills.mapper;

import com.spring.redduck.managebills.dto.SupplierDto;
import com.spring.redduck.managebills.entity.Supplier;

public class SupplierMapper {
    public static SupplierDto mapToSupplierDto(Supplier supplier){
        SupplierDto supplierDto = SupplierDto.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .cif(supplier.getCif())
                .firstQuarter(supplier.getFirstQuarter())
                .secondQuarter(supplier.getSecondQuarter())
                .thirdQuarter(supplier.getThirdQuarter())
                .fourthQuarter(supplier.getFourthQuarter())
                .accumulatedQuantity(supplier.getAccumulatedQuantity())
                .build();
        return supplierDto;
    }

    public static Supplier mapToSupplier(SupplierDto supplierDto) {
        Supplier supplier = Supplier.builder()
                .id(supplierDto.getId())
                .name(supplierDto.getName())
                .cif(supplierDto.getCif())
                .firstQuarter(supplierDto.getFirstQuarter())
                .secondQuarter(supplierDto.getSecondQuarter())
                .thirdQuarter(supplierDto.getThirdQuarter())
                .fourthQuarter(supplierDto.getFourthQuarter())
                .accumulatedQuantity(supplierDto.getAccumulatedQuantity())
                .build();
        return supplier;
    }
}
