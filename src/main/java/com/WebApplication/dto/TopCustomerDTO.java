package com.WebApplication.dto;

import java.math.BigDecimal;

public class TopCustomerDTO {

    private Long customerId;
    private String customerName;
    private BigDecimal totalPurchase;

    public TopCustomerDTO(Long customerId, String customerName, BigDecimal totalPurchase) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalPurchase = totalPurchase;
    }

    public Long getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public BigDecimal getTotalPurchase() { return totalPurchase; }
}