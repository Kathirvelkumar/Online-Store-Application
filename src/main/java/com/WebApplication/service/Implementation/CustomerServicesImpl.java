package com.WebApplication.service.Implementation;

import com.WebApplication.entity.Customers;
import com.WebApplication.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CustomerServicesImpl implements com.WebApplication.service.CustomerServices {

    private final CustomerRepository customerRepository;

    public CustomerServicesImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customers> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customers addCustomer(Customers customer) {
        return customerRepository.save(customer);
    }
}