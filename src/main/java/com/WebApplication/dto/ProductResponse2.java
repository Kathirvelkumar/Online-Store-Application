package com.WebApplication.dto;

import com.WebApplication.entity.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse2 {

    private Long productId;
    private String productName;
    private String description;
    private Products.ProductCategory category;
    private BigDecimal price;
    private Integer stackQuantity;
}