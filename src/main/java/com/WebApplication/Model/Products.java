package com.WebApplication.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private Long productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private int stackQuantity;

}
