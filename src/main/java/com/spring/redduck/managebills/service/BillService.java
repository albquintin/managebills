package com.spring.redduck.managebills.service;

import com.spring.redduck.managebills.dto.BillDto;

import java.util.List;

public interface BillService {

    List<BillDto> findAllBills();
    void createBill(BillDto billDto);
    BillDto findBillById(Long billId);
    void deleteBill(Long billId);
    void updateBill(BillDto billDto);
    List<BillDto> findBillsByMonth(Long month);
}
