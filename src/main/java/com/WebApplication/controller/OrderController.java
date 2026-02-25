package com.WebApplication.controller;

import com.WebApplication.dto.OrderRequest;
import com.WebApplication.dto.OrderResponse;
import com.WebApplication.entity.Orders;
import com.WebApplication.service.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    // GET all orders
    @GetMapping
    public List<Orders> getOrdersList() {
        return orderServices.getOrdersList();
    }

    // GET order by ID
//    @GetMapping("/{orderId}")
//    public Orders getOrderById(@PathVariable Long orderId) {
//        return orderServices.getOrderById(orderId);
//    }

    @GetMapping("/{orderId}")
    ResponseEntity<OrderResponse> getgetOrderById(@PathVariable Long orderId){
        OrderResponse orderResponse = orderServices.getOrderById(orderId);
        return ResponseEntity.status(200).body(orderResponse);
    }

    // POST new order
//    @PostMapping
//    public Orders addOrder(@RequestBody Orders order) {
//        return orderServices.addOrder(order);
//    }

    // DELETE order
    @DeleteMapping("/{orderId}/cancel")
    public void deleteOrder(@PathVariable Long orderId) {
        orderServices.deleteOrder(orderId);
    }


    @PostMapping("/placeOrder")
    ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest request) {

        OrderResponse order = orderServices.placeOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}