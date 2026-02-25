package com.WebApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal totalAmount;


    public enum OrderStatus {
        PENDING,
        PLACED,
        SHIPPED,
        DELIVERED,
        CANCELLED,
        NO_ORDER
    }

//  Extra column created in Order Entity Named by Customer_Id -> FK
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customer;

//  This column Created in OrderItems Entity
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> items;
}
