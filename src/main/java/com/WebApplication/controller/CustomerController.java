package com.WebApplication.controller;

import com.WebApplication.entity.Customers;
import com.WebApplication.service.CustomerServices;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerServices customerServices;

    // Inject interface, not implementation
    public CustomerController(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }

    // GET all customers
    @GetMapping
    public List<Customers> getAllCustomers() {
        return customerServices.findAllCustomers();
    }

    // POST add customer
    @PostMapping
    public Customers addCustomer(@RequestBody Customers customer) {
        return customerServices.addCustomer(customer);
    }
}