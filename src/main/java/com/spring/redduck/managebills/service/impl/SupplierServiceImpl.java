package com.spring.redduck.managebills.service.impl;

import com.spring.redduck.managebills.dto.SupplierDto;
import com.spring.redduck.managebills.entity.Supplier;
import com.spring.redduck.managebills.mapper.SupplierMapper;
import com.spring.redduck.managebills.repository.SupplierRepository;
import com.spring.redduck.managebills.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;
    public SupplierServiceImpl(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }
    @Override
    public List<SupplierDto> findAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream().map((supplier) -> SupplierMapper.mapToSupplierDto(supplier)).collect(Collectors.toList());
    }

    @Override
    public SupplierDto findSupplierById(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).get();
        return SupplierMapper.mapToSupplierDto(supplier);
    }

    @Override
    public void createSupplier(SupplierDto supplierDto) {
        Supplier supplier = SupplierMapper.mapToSupplier(supplierDto);
        supplierRepository.save(supplier);
    }

    @Override
    public void updateSupplier(SupplierDto supplierDto) {
        Supplier supplier = SupplierMapper.mapToSupplier(supplierDto);
        supplierRepository.save(supplier);
    }

    @Override
    public List<SupplierDto> findAllSuppliersWithAccumulatedQuantity() {
        List<Supplier> suppliers = supplierRepository.findAllSuppliersWithAccumulatedQuantity();
        return suppliers.stream().map((supplier) -> SupplierMapper.mapToSupplierDto(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<SupplierDto> findAllOrdered() {
        List<Supplier> suppliers = supplierRepository.findAllOrdered();
        return suppliers.stream().map((supplier) -> SupplierMapper.mapToSupplierDto(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<SupplierDto> findQuantitiesByYear(Long year) {
        List<Supplier> suppliers = supplierRepository.findQuantitiesByYear(year);
        return suppliers.stream().map((supplier) -> SupplierMapper.mapToSupplierDto(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<SupplierDto> findSuppliersFor347Form(Long year) {
        List<Supplier> suppliers = supplierRepository.findSuppliersFor347Form(year);
        return suppliers.stream().map((supplier) -> SupplierMapper.mapToSupplierDto(supplier)).collect(Collectors.toList());
    }
}
