package com.WebApplication.service;

import com.WebApplication.dto.CustomerRequestDTO;
import com.WebApplication.dto.CustomerResponseDTO;
import com.WebApplication.entity.Customers;
import java.util.List;

public interface CustomerServices {

    List<Customers> findAllCustomers();

    CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequest);
}