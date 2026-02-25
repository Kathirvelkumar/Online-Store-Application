package com.WebApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {

    private Long OrderItemsId;
    private String productName;
    private BigDecimal price;
    private Integer quantity;
}
