package com.WebApplication.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class OrderItems {
    @Id
    private Long orderId;
    private int quantity;
    private BigDecimal price;

}
