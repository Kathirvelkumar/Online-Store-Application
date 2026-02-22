package com.WebApplication.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OrderItems {
    @Id
    private int orderId;
    private int quantity;
    private float price;

}
