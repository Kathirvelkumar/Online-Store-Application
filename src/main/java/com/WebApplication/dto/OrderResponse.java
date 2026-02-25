package com.WebApplication.dto;

import com.WebApplication.entity.Orders.OrderStatus;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    @Column(name = "Order Id")
    private Long orderId;

    @Column(name = "Customer Name")
    private String customerName;

    @Column(name = "Order Status")
    private OrderStatus status;

    @Column(name = "Total Price")
    private BigDecimal totalAmount;

    @Column(name = "Placed Date")
    private LocalDateTime orderDate;
    private List<OrderItemResponse> items;
}