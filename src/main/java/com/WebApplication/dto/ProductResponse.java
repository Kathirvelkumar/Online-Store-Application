package com.WebApplication.dto;

import com.WebApplication.entity.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Products.ProductCategory category;

    private List<Products> productDetails;
}
