package com.WebApplication.controller;

import com.WebApplication.dto.CustomerRequestDTO;
import com.WebApplication.dto.CustomerResponseDTO;
import com.WebApplication.entity.Customers;
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
    public List<Customers> getAllCustomers() {

        return customerServices.findAllCustomers();
    }

    // POST add customer
    @PostMapping
    ResponseEntity<CustomerResponseDTO> addCustomer(@RequestBody CustomerRequestDTO customerRequest) {
        CustomerResponseDTO savedCustomer =
                customerServices.addCustomer(customerRequest);
        return ResponseEntity.status(201).body(savedCustomer);
    }
}