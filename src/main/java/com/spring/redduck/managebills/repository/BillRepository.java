package com.spring.redduck.managebills.repository;

import com.spring.redduck.managebills.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
