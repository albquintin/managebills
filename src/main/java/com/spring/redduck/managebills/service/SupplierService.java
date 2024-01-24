package com.spring.redduck.managebills.service;

import com.spring.redduck.managebills.dto.SupplierDto;

import java.util.List;

public interface SupplierService {

    List<SupplierDto> findAllSuppliers();
    SupplierDto findSupplierById(Long supplierId);
    void createSupplier(SupplierDto supplierDto);
    void updateSupplier(SupplierDto supplierDto);
    List<SupplierDto> findAllSuppliersWithAccumulatedQuantity();
    List<SupplierDto> findAllOrdered();
    List<SupplierDto> findQuantitiesByYear(Long year);
}
