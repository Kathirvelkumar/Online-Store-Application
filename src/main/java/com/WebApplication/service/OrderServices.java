package com.WebApplication.service;

import com.WebApplication.dto.TopCustomerDTO;
import com.WebApplication.entity.Orders;
import com.WebApplication.repository.OrderRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServices {

    private final OrderRepository orderRepository;

    public OrderServices(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Fetch all orders
    public List<Orders> getOrdersList() {
        return orderRepository.findAll();
    }

    // Fetch order by ID
    public Orders getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElse(new Orders(
                        0L,
                        LocalDateTime.now(),
                        Orders.OrderStatus.NO_ORDER,
                        BigDecimal.ZERO
                ));
    }

    // Save new order
    public Orders addOrder(Orders order) {
        return orderRepository.save(order);
    }

    // Delete order
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    // Analytics API → Top 3 customers by purchase
    public List<TopCustomerDTO> getTopCustomers() {
        return orderRepository.findTopCustomers((Pageable) PageRequest.of(0, 3));
    }
}