package com.WebApplication.repository;

import com.WebApplication.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {

    @Query(value = "select o.product_id, count(o.product_id) as Top_3_Sold from order_items o group by o.product_id order by Top_3_Sold desc limit 3", nativeQuery = true)
    List<Object[]> getTop3SoldProducts();
}
