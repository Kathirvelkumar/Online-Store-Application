package com.WebApplication.service.Implementation;

import com.WebApplication.dto.CustomerRequest;
import com.WebApplication.dto.CustomerResponse;
import com.WebApplication.entity.Customers;
import com.WebApplication.repository.CustomerRepository;
import com.WebApplication.service.CustomerServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServicesImpl implements CustomerServices {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CustomerResponse> findAllCustomers() {
        List<Customers> customers = customerRepository.findAll();
        return customers.stream().map(customer -> mapper.map(customer, CustomerResponse.class)).toList();
    }

    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        Customers customers = mapper.map(customerRequest, Customers.class);

        Customers savedCustomer = customerRepository.save(customers);

        // Convert Entity → Response DTO
        return mapper.map(savedCustomer, CustomerResponse.class);
    }

    @Override
    public List<CustomerResponse> addMultipleCustomers(List<CustomerRequest> customerRequests) {
        List<Customers> customers = customerRequests.stream().map(req -> mapper.map(req, Customers.class)).toList();
        List<Customers> savedCustomers = customerRepository.saveAll(customers);

        return savedCustomers.stream().map(customer -> mapper.map(customer, CustomerResponse.class)).toList();
    }

    @Override
    public CustomerResponse findCustomerById(Long id) {
        Customers customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        return mapper.map(customer, CustomerResponse.class);

    }


}