package com.spring.redduck.managebills.repository;

import com.spring.redduck.managebills.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query("SELECT b FROM Bill b WHERE MONTH(b.billDate) = :month ORDER BY b.billDate")
    List<Bill> findBillsByMonth(Long month);

    @Query("SELECT b FROM Bill b ORDER BY b.billDate")
    List<Bill> findAllBillsOrdered();
}
