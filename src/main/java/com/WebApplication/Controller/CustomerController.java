package com.WebApplication.Controller;

import com.WebApplication.Model.Customers;
import com.WebApplication.Service.CustomerServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerServices customerServices;

//    Dependency Injection
    public CustomerController(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }

    @GetMapping("/api/customers")
    public List<Customers> getAllCustomers() {
        return customerServices.findAllCustomers();
    }

    @PostMapping("/api/customers")
    public Customers addCustomer(@RequestBody Customers customer) {
        return customerServices.addCustomer(customer);
    }
}
