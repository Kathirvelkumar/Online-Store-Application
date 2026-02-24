package com.WebApplication.service;

import com.WebApplication.entity.Products;
import java.util.List;

public interface ProductServices {

    List<Products> getProductsList();

    Products getProductById(Long productId);

    Products addProduct(Products product);

    List<Products> addAllProducts(List<Products> products);
}