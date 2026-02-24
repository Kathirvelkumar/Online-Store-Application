package com.WebApplication.controller;

import com.WebApplication.entity.Customers;
import com.WebApplication.service.Implementation.CustomerServices;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController                     // Marks this class as REST controller
@RequestMapping("/api/customers")   // Base URL for all endpoints in this controller
public class CustomerController {

    private final CustomerServices customerServices;

    // Constructor Dependency Injection (recommended way)
    public CustomerController(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }

    // GET: Fetch all customers from database
    // URL -> GET /api/customers
    @GetMapping
    public List<Customers> getAllCustomers() {
        return customerServices.findAllCustomers();
    }

    // POST: Add a new customer to database
    // URL -> POST /api/customers
    @PostMapping
    public Customers addCustomer(@RequestBody Customers customer) {
        return customerServices.addCustomer(customer);
    }
}