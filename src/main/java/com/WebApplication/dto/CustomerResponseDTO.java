package com.WebApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    private String customerName;
    private String customerEmail;
    private String password;
    private String phoneNumber;
    private String customerAddress;
}
