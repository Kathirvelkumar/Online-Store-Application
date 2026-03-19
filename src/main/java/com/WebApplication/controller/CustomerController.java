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
import java.util.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;

    @Autowired
    private OrderServices orderServices;

    // GET all customers
    @GetMapping
    ResponseEntity<List<CustomerResponse>> findAllCustomers(){
        List<CustomerResponse> customerResponse = customerServices.findAllCustomers();
        return ResponseEntity.status(200).body(customerResponse);
    }

//    @GetMapping
//    public ResponseEntity<Page<CustomerResponse>> findAllCustomers(Pageable pageable){
//        Page<CustomerResponse> customerResponse = customerServices.findAllCustomers(pageable);
//        return ResponseEntity.ok(customerResponse);
//    }

    @GetMapping("/testPage")
    public ResponseEntity<Page<CustomerResponse>> testPage(Pageable pageable){
        Page<CustomerResponse> customerResponse = customerServices.testPage(pageable);
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> findCustomerById(@PathVariable Long id){
        CustomerResponse customerResponse = customerServices.findCustomerById(id);
        return ResponseEntity.status(200).body(customerResponse);
    }

    // POST add customer
    @PostMapping("/register")
    ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse savedCustomer =
                customerServices.addCustomer(customerRequest);
        return ResponseEntity.status(201).body(savedCustomer);
    }

    // POST Bulk Customer.
    @PostMapping("/bulk")
    ResponseEntity<List<CustomerResponse>> addMultipleCustomers(@RequestBody List<CustomerRequest> customerRequests){
        List<CustomerResponse> customerResponses = customerServices.addMultipleCustomers(customerRequests);
        return ResponseEntity.status(201).body(customerResponses);
    }

    @GetMapping("/frequency")
    ResponseEntity<List<CustomerResponse>> getCustomersByFrequency(
            @RequestParam long minOrders){

        return ResponseEntity.ok(orderServices.getMoreThenNorder(minOrders));
    }

    @GetMapping("/TotalRevenue")
    ResponseEntity<Map<CustomerResponse, BigDecimal>> totalRevenuePerCustomer(){
        return ResponseEntity.ok(orderServices.totalRevenuePerCustomer());
    }
}