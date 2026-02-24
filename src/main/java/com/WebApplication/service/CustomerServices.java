package com.WebApplication.service;

import com.WebApplication.entity.Customers;
import java.util.List;

public interface CustomerServices {

    List<Customers> findAllCustomers();

    Customers addCustomer(Customers customer);
}