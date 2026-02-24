package com.WebApplication.service;

import com.WebApplication.dto.CustomerRequest;
import com.WebApplication.dto.CustomerResponse;

import java.util.List;

public interface CustomerServices {

    List<CustomerResponse> findAllCustomers();

    CustomerResponse addCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> addMultipleCustomers(List<CustomerRequest> customerRequests);

    CustomerResponse findCustomerById(Long id);
}