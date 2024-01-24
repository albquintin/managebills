package com.spring.redduck.managebills.repository;

import com.spring.redduck.managebills.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(value = "SELECT s.id, s.supplier_name, s.cif,  (SELECT ROUND(SUM(b.total_price), 2) FROM db_managebills.bills as b WHERE b.supplier_id = s.id AND YEAR(b.bill_date) = YEAR(CURDATE())) as accumulated_quantity\n" +
            "FROM db_managebills.suppliers as s", nativeQuery = true)
    List<Supplier>findAllSuppliersWithAccumulatedQuantity();
    @Query("SELECT s FROM Supplier s ORDER BY s.name")
    List<Supplier>findAllOrdered();
    @Query(value = "SELECT s.id, s.supplier_name, s.cif,  (SELECT ROUND(SUM(b.total_price), 2) FROM db_managebills.bills as b WHERE b.supplier_id = s.id AND YEAR(b.bill_date) = :year) as accumulated_quantity\n" +
            "FROM db_managebills.suppliers as s", nativeQuery = true)
    List<Supplier>findQuantitiesByYear(Long year);
}
