package com.WebApplication.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    @Column(name = "Customer Name")
    private String customerName;

    @Column(name = "Customer Address")
    private String customerAddress;
}
