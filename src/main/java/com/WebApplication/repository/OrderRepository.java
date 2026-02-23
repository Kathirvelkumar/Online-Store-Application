package com.WebApplication.repository;

import com.WebApplication.dto.TopCustomerDTO;
import com.WebApplication.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import org.springframework.data.domain.Pageable;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("""
       SELECT new com.WebApplication.dto.TopCustomerDTO(
           c.customerId,
           c.customerName,
           SUM(o.totalAmount)
       )
       FROM Orders o
       JOIN o.customer c
       GROUP BY c.customerId, c.customerName
       ORDER BY SUM(o.totalAmount) DESC
       """)
    List<TopCustomerDTO> findTopCustomers(Pageable pageable);
}
