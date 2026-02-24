package com.WebApplication.repository;

import com.WebApplication.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import org.springframework.data.domain.Pageable;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

}
