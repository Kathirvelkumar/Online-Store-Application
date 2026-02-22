package com.WebApplication.Service;

import com.WebApplication.Model.Customers;
import com.WebApplication.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServices {

    CustomerRepository customerRepository;

    public CustomerServices(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
//    Show the Entire Customer List
    public List<Customers> findAllCustomers() {
        return customerRepository.findAll();
    }

//    Add a new Customer
    public Customers addCustomer(Customers customer) {
        return customerRepository.save(customer);
    }
}
