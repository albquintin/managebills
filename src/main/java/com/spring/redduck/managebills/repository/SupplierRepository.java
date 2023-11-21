package com.spring.redduck.managebills.repository;

import com.spring.redduck.managebills.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
