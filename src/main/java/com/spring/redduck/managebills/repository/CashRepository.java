package com.spring.redduck.managebills.repository;

import com.spring.redduck.managebills.entity.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CashRepository extends JpaRepository<Cash, Long> {
    @Query("SELECT c FROM Cash c WHERE MONTH(c.paymentDate) = :month AND YEAR(c.paymentDate) = :year AND c.iva10amount > 0 ORDER BY c.paymentDate")
    List<Cash> findCashByMonth(Long month, Long year);
    @Query("SELECT c FROM Cash c WHERE MONTH(c.paymentDate) = :month AND YEAR(c.paymentDate) = :year AND c.iva21amount > 0")
    Cash findCodiasaCashByMonth(Long month, Long year);
    @Query(value = "SELECT c FROM Cash c WHERE YEAR(c.paymentDate) = YEAR(CURDATE())")
    List<Cash> findCashOfCurrentYear();
    @Query("SELECT c FROM Cash c WHERE YEAR(c.paymentDate) < YEAR(CURDATE())")
    List<Cash> findOldCash();
    @Query("SELECT c.paymentDate, c.totalPrice FROM Cash c")
    List<Cash> findGraphicData();
}
