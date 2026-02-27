package com.WebApplication.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @Email(message = "Invalid email format")
    @NotBlank
    @Column(unique = true, nullable = false)
    private String customerEmail;

    private String password;
    private String phoneNumber;
    private String customerAddress;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (status == null) status = CustomerStatus.ACTIVE;
        createdAt = LocalDateTime.now();
    }

    public enum CustomerStatus {
        ACTIVE,
        INACTIVE,
        BLOCKED
    }

}
