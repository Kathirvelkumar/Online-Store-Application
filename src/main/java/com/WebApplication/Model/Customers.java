package com.WebApplication.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customers {
    @Id
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String password;
    private String phoneNumber;
    private String customerAddress;

}
