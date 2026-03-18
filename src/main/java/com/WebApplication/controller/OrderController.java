package com.WebApplication.controller;

import com.WebApplication.dto.CustomerResponse;
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
    public List<OrderResponse> getOrdersList() {
        return orderServices.getOrdersList();
    }

    @GetMapping("/{orderId}")
    ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId){
        OrderResponse orderResponse = orderServices.getOrderById(orderId);
        return ResponseEntity.status(200).body(orderResponse);
    }

    // DELETE order
    @DeleteMapping("/{orderId}/cancel")
    public void deleteOrder(@PathVariable Long orderId) {
        orderServices.deleteOrder(orderId);
    }

//  Create order
    @PostMapping("/placeOrder")
    ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest request) {

        OrderResponse order = orderServices.placeOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

//  Cancel the Order used by OrderId
    @PutMapping("/{orderId}/cancel")
    ResponseEntity<OrderResponse> cancelOrderById(@PathVariable Long orderId){
        OrderResponse orderResponse = orderServices.cancelOrderById(orderId);
        return ResponseEntity.ok(orderResponse);
    }

//  Get TOP 3 customers based on the purchase price
    @GetMapping("/analytics/top-customers")
    ResponseEntity<List<CustomerResponse>> getTop3Customers(){
        List<CustomerResponse> customerResponse = orderServices.getTop3Customers();
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping("/top5Orders")
    ResponseEntity<List<OrderResponse>> getTop5Orders(){
        List<OrderResponse> orderResponses = orderServices.getTop5Orders();
        return ResponseEntity.ok(orderResponses);
    }

    @GetMapping("/last7DaysOrders")
    ResponseEntity<List<Orders>> last7DaysOrders(){
        List<Orders> orders = orderServices.getLast7DaysOrders();
        return ResponseEntity.ok(orders);
    }

}