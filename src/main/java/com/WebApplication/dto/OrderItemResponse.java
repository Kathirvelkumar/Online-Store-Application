package com.WebApplication.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemResponse {

    private Long OrderItemId;
    private String productName;
    private BigDecimal price;
    private Integer quantity;
}
