package com.WebApplication.controller;

import com.WebApplication.model.Products;
import com.WebApplication.service.ProductServices;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController                          // Marks this class as REST controller
@RequestMapping("/api/products")         // Base URL for all product endpoints
public class ProductController {

    private final ProductServices productServices;

    // Constructor-based Dependency Injection (recommended)
    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    // GET: Fetch all products
    // URL -> GET /api/products
    @GetMapping
    public List<Products> getProducts() {
        return productServices.getProductsList();
    }

    // GET: Fetch product by ID
    // URL -> GET /api/products/{productId}
    @GetMapping("/{productId}")
    public Products getProductById(@PathVariable Long productId) {
        return productServices.getProductById(productId);
    }

    // POST: Add a single product
    // URL -> POST /api/products
    @PostMapping
    public Products addProduct(@RequestBody Products product) {
        return productServices.addProduct(product);
    }

    // POST: Add multiple products at once
    // URL -> POST /api/products/bulk
    @PostMapping("/bulk")
    public List<Products> addProducts(@RequestBody List<Products> products) {
        return productServices.addAllProducts(products);
    }
}


//
//    @PutMapping("/products/{productId}")
//    public void updateProduct(@PathVariable int productId,
//                              @RequestBody Products product) {
//        product.setProductId(productId);
//        productServices.updateProduct(product);
//    }
//
//    @DeleteMapping("/products/{productId}")
//    public void deleteProduct(@PathVariable int productId) {
//        productServices.deleteProduct(productId);
//    }
