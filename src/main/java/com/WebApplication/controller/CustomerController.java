package com.WebApplication.controller;

import com.WebApplication.dto.CustomerRequest;
import com.WebApplication.dto.CustomerResponse;
import com.WebApplication.service.CustomerServices;
import com.WebApplication.service.OrderServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerServices customerServices;
    private final OrderServices orderServices;

    // ✅ Constructor Injection (BEST PRACTICE)
    @Autowired
    public CustomerController(CustomerServices customerServices, OrderServices orderServices) {
        this.customerServices = customerServices;
        this.orderServices = orderServices;
    }

    // GET all customers
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomers() {
        List<CustomerResponse> customerResponse = customerServices.findAllCustomers();
        return ResponseEntity.ok(customerResponse);
    }

    // Pagination API
    @GetMapping("/testPage")
    public ResponseEntity<Page<CustomerResponse>> testPage(Pageable pageable) {
        Page<CustomerResponse> customerResponse = customerServices.testPage(pageable);
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable Long id) {
        CustomerResponse customerResponse = customerServices.findCustomerById(id);
        return ResponseEntity.ok(customerResponse);
    }

    // POST add customer
    @PostMapping("/register")
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse savedCustomer = customerServices.addCustomer(customerRequest);
        return ResponseEntity.status(201).body(savedCustomer);
    }

    // POST Bulk Customer
    @PostMapping("/bulk")
    public ResponseEntity<List<CustomerResponse>> addMultipleCustomers(@RequestBody List<CustomerRequest> customerRequests) {
        List<CustomerResponse> customerResponses = customerServices.addMultipleCustomers(customerRequests);
        return ResponseEntity.status(201).body(customerResponses);
    }

//    @GetMapping("/frequency")
//    public ResponseEntity<List<CustomerResponse>> getCustomersByFrequency(@RequestParam long minOrders) {
//        return ResponseEntity.ok(orderServices.getMoreThanNOrders(minOrders));
//    }

    @GetMapping("/total-revenue")
    public ResponseEntity<Map<CustomerResponse, BigDecimal>> totalRevenuePerCustomer() {
        return ResponseEntity.ok(orderServices.totalRevenuePerCustomer());
    }
}