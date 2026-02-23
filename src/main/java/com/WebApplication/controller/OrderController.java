package com.WebApplication.controller;

import com.WebApplication.dto.TopCustomerDTO;
import com.WebApplication.entity.Orders;
import com.WebApplication.service.OrderServices;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController                           // Marks this class as REST API controller
@RequestMapping("/api/orders")            // Base URL for all endpoints here
public class OrderController {

    private final OrderServices orderServices;

    // Constructor Dependency Injection (best practice)
    public OrderController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    // GET: Fetch all orders
    // URL -> GET /api/orders
    @GetMapping
    public List<Orders> getOrdersList() {
        return orderServices.getOrdersList();
    }

    // GET: Fetch a single order by ID
    // URL -> GET /api/orders/{orderId}
    @GetMapping("/{orderId}")
    public Orders getOrderById(@PathVariable Long orderId){
        return orderServices.getOrderById(orderId);
    }

    // POST: Create a new order
    // URL -> POST /api/orders
    @PostMapping
    public Orders addOrder(@RequestBody Orders order) {
        return orderServices.addOrder(order);
    }

    // DELETE: Cancel/Delete order by ID
    // URL -> DELETE /api/orders/{orderId}/cancel
    @DeleteMapping("/{orderId}/cancel")
    public void deleteOrder(@PathVariable Long orderId){
        orderServices.deleteOrder(orderId);
    }

    @GetMapping("/analytics/top-customers")
    public List<TopCustomerDTO> getTopCustomers() {
        return orderServices.getTopCustomers();
    }

}