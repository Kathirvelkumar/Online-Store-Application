package com.WebApplication.controller;

import com.WebApplication.dto.CustomerRequest;
import com.WebApplication.dto.CustomerResponse;
import com.WebApplication.service.CustomerServices;
import com.WebApplication.service.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<CustomerResponse>> getCustomersByFrequency(
            @RequestParam long minOrders){

        return ResponseEntity.ok(orderServices.getMoreThenNorder(minOrders));
    }
}