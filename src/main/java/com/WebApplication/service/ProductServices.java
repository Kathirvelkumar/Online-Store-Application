package com.WebApplication.service;

import com.WebApplication.dto.ProductResponse;
import com.WebApplication.dto.ProductResponse2;
import com.WebApplication.entity.Products;

import java.math.BigDecimal;
import java.util.List;

public interface ProductServices {

    List<Products> getProductsList();

    Products getProductById(Long productId);

    Products addProduct(Products product);

    List<Products> addAllProducts(List<Products> products);

    List<ProductResponse> getCategoryProducts();

    ProductResponse getProductsByCategory(Products.ProductCategory category);

    ProductResponse2 deleteProductById(Long productId);

    List<ProductResponse2> getSortedProductsByName();

    List<ProductResponse2> getSortedProductsByPrice();

    List<ProductResponse2> filterGreater(BigDecimal price);

    List<ProductResponse2> filterSmaller(BigDecimal price);
}