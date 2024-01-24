package com.spring.redduck.managebills.service.impl;

import com.spring.redduck.managebills.dto.BillDto;
import com.spring.redduck.managebills.entity.Bill;
import com.spring.redduck.managebills.entity.Supplier;
import com.spring.redduck.managebills.mapper.BillMapper;
import com.spring.redduck.managebills.repository.BillRepository;
import com.spring.redduck.managebills.repository.SupplierRepository;
import com.spring.redduck.managebills.service.BillService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    private BillRepository billRepository;
    private SupplierRepository supplierRepository;

    public BillServiceImpl(BillRepository billRepository, SupplierRepository supplierRepository){
        this.billRepository = billRepository;
        this.supplierRepository = supplierRepository;
    }
    @Override
    public List<BillDto> findAllBills() {
        List<Bill>bills = billRepository.findAll();
        return bills.stream().map((bill) -> BillMapper.mapToBillDto(bill)).collect(Collectors.toList());
    }

    @Override
    public void createBill(BillDto billDto) {
        Optional<Supplier> supplier = supplierRepository.findById(billDto.getSupplierId());
        Bill bill = BillMapper.mapToBill(billDto, supplier.get());
        billRepository.save(bill);
    }

    @Override
    public BillDto findBillById(Long billId) {
        Bill bill = billRepository.findById(billId).get();
        return BillMapper.mapToBillDto(bill);
    }

    @Override
    public void deleteBill(Long billId) {
        billRepository.deleteById(billId);
    }

    @Override
    public void updateBill(BillDto billDto) {
        Supplier supplier = supplierRepository.findById(billDto.getSupplierId()).get();
        Bill bill = BillMapper.mapToBill(billDto, supplier);
        billRepository.save(bill);
    }

    @Override
    public List<BillDto> findBillsByMonth(Long month) {
        List<Bill> bills = billRepository.findBillsByMonth(month);
        return bills.stream().map((bill) -> BillMapper.mapToBillDto(bill)).collect(Collectors.toList());
    }

    @Override
    public Optional<BillDto> findBillByOrderNumber(Long orderNumber) {
        Bill bill = billRepository.findBillByOrderNumber(orderNumber);
        if(bill == null)
            return Optional.empty();
        else
            return Optional.of(BillMapper.mapToBillDto(bill));
    }

    @Override
    public Optional<BillDto> findBillByBillNumber(String billNumber) {
        Bill bill = billRepository.findBillByBillNumber(billNumber);
        if(bill == null)
            return Optional.empty();
        else
            return Optional.of(BillMapper.mapToBillDto(bill));
    }

    @Override
    public List<BillDto> findBillsBySupplier(Long supplierId) {
        List<Bill>bills = billRepository.findBillsBySupplier(supplierId);
        return bills.stream().map((bill) -> BillMapper.mapToBillDto(bill)).collect(Collectors.toList());
    }
}
