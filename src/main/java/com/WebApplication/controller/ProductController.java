package com.WebApplication.controller;

import com.WebApplication.entity.Products;
import com.WebApplication.service.ProductServices;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductServices productServices;

    // Constructor Injection
    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    // GET: Fetch all products
    @GetMapping
    public List<Products> getProducts() {
        return productServices.getProductsList();
    }

    // GET: Fetch product by ID
    @GetMapping("/{productId}")
    public Products getProductById(@PathVariable Long productId) {
        return productServices.getProductById(productId);
    }

    // POST: Add a single product
    @PostMapping
    public Products addProduct(@RequestBody Products product) {
        return productServices.addProduct(product);
    }

    // POST: Add multiple products
    @PostMapping("/bulk")
    public List<Products> addProducts(@RequestBody List<Products> products) {
        return productServices.addAllProducts(products);
    }
}