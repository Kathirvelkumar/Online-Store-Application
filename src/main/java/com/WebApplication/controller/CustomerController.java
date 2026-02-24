package com.WebApplication.controller;

import com.WebApplication.dto.CustomerRequest;
import com.WebApplication.dto.CustomerResponse;
import com.WebApplication.service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;


    // GET all customers
    @GetMapping
    ResponseEntity<List<CustomerResponse>> findAllCustomers(){
        List<CustomerResponse> customerResponse = customerServices.findAllCustomers();
        return ResponseEntity.status(200).body(customerResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> findCustomer(@PathVariable Long id){
        CustomerResponse customerResponse = customerServices.findCustomer(id);
        return ResponseEntity.status(200).body(customerResponse);
    }

    // POST add customer
    @PostMapping
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
}