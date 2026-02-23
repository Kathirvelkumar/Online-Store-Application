package com.WebApplication.service;

import com.WebApplication.entity.Orders;
import com.WebApplication.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service   // Marks this class as Service layer component
public class OrderServices {

    private final OrderRepository orderRepository;

    // Constructor-based Dependency Injection
    public OrderServices(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /*
     * Fetch all orders from database
     * @return list of orders
     */
    public List<Orders> getOrdersList() {
        return orderRepository.findAll();
    }

    /*
     * Fetch order by ID
     * If not found, returns a default NO_ORDER object (your current logic)
     */
    public Orders getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElse(new Orders(
                        0L,
                        LocalDateTime.now(),
                        Orders.OrderStatus.NO_ORDER,
                        BigDecimal.ZERO
                ));
    }

    //    Save a new order in database
    public Orders addOrder(Orders order) {
        return orderRepository.save(order);
    }

    //  Delete order by ID

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}