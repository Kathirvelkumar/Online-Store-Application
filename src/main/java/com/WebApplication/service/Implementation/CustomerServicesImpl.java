package com.WebApplication.service.Implementation;

import com.WebApplication.dto.CustomerRequestDTO;
import com.WebApplication.dto.CustomerResponseDTO;
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
    public List<Customers> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequest) {

        Customers customers = mapper.map(customerRequest, Customers.class);

        Customers savedCustomer = customerRepository.save(customers);

        // Convert Entity → Response DTO
        return mapper.map(savedCustomer, CustomerResponseDTO.class);
    }



}