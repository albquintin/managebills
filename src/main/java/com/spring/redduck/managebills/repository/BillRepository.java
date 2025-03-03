package com.spring.redduck.managebills.repository;

import com.spring.redduck.managebills.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query("SELECT b FROM Bill b WHERE MONTH(b.billDate) = :month AND YEAR(b.billDate) = :year ORDER BY b.billDate")
    List<Bill> findBillsByMonth(Long month, Long year);
    @Query("SELECT b FROM Bill b WHERE b.orderNumber = :orderNumber AND YEAR(b.billDate) = :year")
    Bill findBillByOrderNumberAndYear(Long orderNumber, Long year);
    @Query("SELECT b FROM Bill b WHERE b.billNumber = :billNumber")
    Bill findBillByBillNumber(String billNumber);
    @Query("SELECT b FROM Bill b WHERE b.supplier.id = :supplierId AND YEAR(b.billDate) = :year")
    List<Bill> findBillsBySupplierAndYear(Long supplierId, Long year);
    @Query(value = "SELECT b FROM Bill b WHERE YEAR(b.billDate) = YEAR(CURDATE())")
    List<Bill> findBillsOfCurrentYear();
    @Query(value = "SELECT b FROM Bill b WHERE YEAR(b.billDate) < YEAR(CURDATE())")
    List<Bill> findOldBills();
}
