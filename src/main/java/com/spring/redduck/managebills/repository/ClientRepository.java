package com.spring.redduck.managebills.repository;

import com.spring.redduck.managebills.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "SELECT c.id, c.client_name, c.dni, c.address, c.zip_code, c.city,  (SELECT ROUND(SUM(p.total_price), 2) FROM db_managebills.payments as p WHERE p.client_id = c.id) as accumulated_quantity\n" +
            "FROM db_managebills.clients as c", nativeQuery = true)
    List<Client>findAllClientsWithAccumulatedQuantity();

    @Query("SELECT c FROM Client c ORDER BY c.name")
    List<Client> findAllOrdered();
}
