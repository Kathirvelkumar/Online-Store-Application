package com.WebApplication.Controller;

import com.WebApplication.Model.Orders;
import com.WebApplication.Service.OrderServices;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class OrderController {

    OrderServices orderServices;

    public OrderController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

//    Get all orders
    @GetMapping("/api/orders")
    public List<Orders> getOrdersList() {
        return orderServices.getOrdersList();
    }

//    Get a Order based on OrderId
    @GetMapping("/api.orders/{orderId}")
    public Orders getOrderById(@PathVariable Long orderId){
        return orderServices.getOrderById(orderId);
    }

//    Create a Order
    @PostMapping("/api/orders")
    public void addOrder(Orders orders) {
        orderServices.addOrder(orders);
    }

//    Delete a Order Based on ID
    @DeleteMapping("/api/orders/{orderId}/cancel")
    public void deleteOrder(@PathVariable Long orderId){
        orderServices.deleteOrder(orderId);
    }

//
}
