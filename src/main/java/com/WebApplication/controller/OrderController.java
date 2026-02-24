package com.WebApplication.controller;

import com.WebApplication.entity.Orders;
import com.WebApplication.service.OrderServices;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderServices orderServices;

    // Inject interface instead of implementation
    public OrderController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    // GET all orders
    @GetMapping
    public List<Orders> getOrdersList() {
        return orderServices.getOrdersList();
    }

    // GET order by ID
    @GetMapping("/{orderId}")
    public Orders getOrderById(@PathVariable Long orderId){
        return orderServices.getOrderById(orderId);
    }

    // POST new order
    @PostMapping
    public Orders addOrder(@RequestBody Orders order) {
        return orderServices.addOrder(order);
    }

    // DELETE order
    @DeleteMapping("/{orderId}/cancel")
    public void deleteOrder(@PathVariable Long orderId){
        orderServices.deleteOrder(orderId);
    }
}