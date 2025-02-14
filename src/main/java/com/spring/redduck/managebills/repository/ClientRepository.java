package com.spring.redduck.managebills.repository;

import com.spring.redduck.managebills.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "SELECT c.id, c.client_name, c.dni, c.address, c.zip_code, c.city, c.first_quarter, c.second_quarter, c.third_quarter, c.fourth_quarter, " +
            "(SELECT ROUND(SUM(p.total_price), 2) " +
            "FROM db_managebills.payments as p WHERE p.client_id = c.id AND YEAR(p.payment_date) = YEAR(CURDATE())) as accumulated_quantity " +
            "FROM db_managebills.clients as c", nativeQuery = true)
    List<Client> findAllClientsWithAccumulatedQuantity();

    @Query("SELECT c FROM Client c ORDER BY c.name")
    List<Client> findAllOrdered();
    @Query(value = "SELECT c.id, c.client_name, c.dni, c.address, c.zip_code, c.city, c.first_quarter, c.second_quarter, c.third_quarter, c.fourth_quarter, " +
            "(SELECT ROUND(SUM(p.total_price), 2) " +
            "FROM db_managebills.payments as p WHERE p.client_id = c.id AND YEAR(p.payment_date) = :year) as accumulated_quantity " +
            "FROM db_managebills.clients as c", nativeQuery = true)
    List<Client> findQuantitiesByYear(Long year);
    @Query(value = "SELECT c.id, c.client_name, c.dni, c.address, c.zip_code, c.city,  " +
            "(SELECT ROUND(SUM(p.total_price), 2) FROM db_managebills.payments as p WHERE p.client_id = c.id AND YEAR(p.payment_date) = :year) as accumulated_quantity, " +
            "(SELECT ROUND(SUM(p.total_price), 2) FROM db_managebills.payments as p WHERE p.client_id = c.id AND YEAR(p.payment_date) = :year AND MONTH(p.payment_date) BETWEEN 1 AND 3) as first_quarter, " +
            "(SELECT ROUND(SUM(p.total_price), 2) FROM db_managebills.payments as p WHERE p.client_id = c.id AND YEAR(p.payment_date) = :year AND MONTH(p.payment_date) BETWEEN 4 AND 6) as second_quarter, " +
            "(SELECT ROUND(SUM(p.total_price), 2) FROM db_managebills.payments as p WHERE p.client_id = c.id AND YEAR(p.payment_date) = :year AND MONTH(p.payment_date) BETWEEN 7 AND 9) as third_quarter, " +
            "(SELECT ROUND(SUM(p.total_price), 2) FROM db_managebills.payments as p WHERE p.client_id = c.id AND YEAR(p.payment_date) = :year AND MONTH(p.payment_date) BETWEEN 10 AND 12) as fourth_quarter " +
            "FROM db_managebills.clients as c " +
            "WHERE (SELECT ROUND(SUM(p.total_price), 2) FROM db_managebills.payments as p WHERE p.client_id = c.id AND YEAR(p.payment_date) = :year) >= 3000",
            nativeQuery = true)
    List<Client> findClientsFor347Form(Long year);
}
