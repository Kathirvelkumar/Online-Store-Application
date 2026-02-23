package com.WebApplication.service;

import com.WebApplication.model.Customers;
import com.WebApplication.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service   // Marks this class as a Service layer component
public class CustomerServices {

    private final CustomerRepository customerRepository;

    // Constructor Dependency Injection (recommended way)
    public CustomerServices(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /*
     * Fetch all customers from database
     * @return List of all customers
     */
    public List<Customers> findAllCustomers() {
        return customerRepository.findAll();
    }

    /*
     * Save a new customer into database
     * @param customer - Customer object from request
     * @return Saved customer with generated ID
     */
    public Customers addCustomer(Customers customer) {
        return customerRepository.save(customer);
    }
}