package com.WebApplication.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Order {
    @Id
    private int orderId;
    private String orderDate;
    //    private LocalDateTime orderDate;
    private OrderStatus status;
    private float totalAmount;

    public enum OrderStatus {
        PLACED,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }
}

