package com.spring.redduck.managebills.repository;

import com.spring.redduck.managebills.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(value = "SELECT s.id, s.supplier_name, s.cif, s.first_quarter, s.second_quarter, s.third_quarter, s.fourth_quarter, (SELECT ROUND(SUM(b.total_price), 2) FROM db_managebills.bills as b WHERE b.supplier_id = s.id AND YEAR(b.bill_date) = YEAR(CURDATE())) as accumulated_quantity\n" +
            "FROM db_managebills.suppliers as s", nativeQuery = true)
    List<Supplier>findAllSuppliersWithAccumulatedQuantity();
    @Query("SELECT s FROM Supplier s ORDER BY s.name")
    List<Supplier>findAllOrdered();
    @Query(value = "SELECT s.id, s.supplier_name, s.cif, s.first_quarter, s.second_quarter, s.third_quarter, s.fourth_quarter, (SELECT ROUND(SUM(b.total_price), 2) FROM db_managebills.bills as b WHERE b.supplier_id = s.id AND YEAR(b.bill_date) = :year) as accumulated_quantity\n" +
            "FROM db_managebills.suppliers as s", nativeQuery = true)
    List<Supplier>findQuantitiesByYear(Long year);

    @Query(value = "SELECT s.id, s.supplier_name, s.cif, " +
            "(SELECT ROUND(SUM(b.total_price), 2) FROM db_managebills.bills as b WHERE b.supplier_id = s.id AND YEAR(b.bill_date) = YEAR(CURDATE())) as accumulated_quantity, " +
            "(SELECT ROUND(SUM(b.total_price), 2) FROM db_managebills.bills as b WHERE b.supplier_id = s.id AND YEAR(b.bill_date) = YEAR(CURDATE()) AND MONTH(b.bill_date) BETWEEN 1 AND 3) as first_quarter, " +
            "(SELECT ROUND(SUM(b.total_price), 2) FROM db_managebills.bills as b WHERE b.supplier_id = s.id AND YEAR(b.bill_date) = YEAR(CURDATE()) AND MONTH(b.bill_date) BETWEEN 4 AND 6) as second_quarter, " +
            "(SELECT ROUND(SUM(b.total_price), 2) FROM db_managebills.bills as b WHERE b.supplier_id = s.id AND YEAR(b.bill_date) = YEAR(CURDATE()) AND MONTH(b.bill_date) BETWEEN 7 AND 9) as third_quarter, " +
            "(SELECT ROUND(SUM(b.total_price), 2) FROM db_managebills.bills as b WHERE b.supplier_id = s.id AND YEAR(b.bill_date) = YEAR(CURDATE()) AND MONTH(b.bill_date) BETWEEN 10 AND 12) as fourth_quarter " +
            "FROM db_managebills.suppliers as s " +
            "WHERE (SELECT ROUND(SUM(b.total_price), 2) FROM db_managebills.bills as b WHERE b.supplier_id = s.id AND YEAR(b.bill_date) = YEAR(CURDATE())) >= 3000",
            nativeQuery = true)
    List<Supplier>findSuppliersFor347Form();
}
