package com.WebApplication.service;

import com.WebApplication.dto.CustomerRequest;
import com.WebApplication.dto.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerServices {

    List<CustomerResponse> findAllCustomers();

    Page<CustomerResponse> testPage(Pageable pageable);

//    Page<CustomerResponse> findAllCustomers(Pageable pageable);

    CustomerResponse addCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> addMultipleCustomers(List<CustomerRequest> customerRequests);

    CustomerResponse findCustomerById(Long id);
}