package com.WebApplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank(message = "Product name is required")
    private String productName;

    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category is required")
    private ProductCategory category;

    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be >= 0")
    private BigDecimal price;

    @Min(value = 0, message = "Stock must be >= 0")
    private int stackQuantity;

    public enum ProductCategory {
        ELECTRONICS,
        HOME_APPLIANCE,
        ACCESSORIES,
        BOOKS,
        FURNITURE,
        CLOTHING
    }
}
