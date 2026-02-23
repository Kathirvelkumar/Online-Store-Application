package com.WebApplication.service;

import com.WebApplication.model.Products;
import com.WebApplication.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service   // Marks this class as a Service layer component
public class ProductServices {

    private final ProductRepository productRepository;

    // Constructor Dependency Injection (recommended)
    public ProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*
     * Fetch all products from database
     * @return list of products
     */
    public List<Products> getProductsList() {
        return productRepository.findAll();
    }

    /*
     * Fetch a product by ID
     * If not found, returns a default placeholder product
     */
    public Products getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElse(new Products(
                        0L,
                        "Not Found",
                        "No Description",
                        BigDecimal.ZERO,
                        0
                ));
    }

    //    Save a single product to database
    public Products addProduct(Products product) {
        return productRepository.save(product);
    }

    //    Save multiple products to database
    public List<Products> addAllProducts(List<Products> products) {
        return productRepository.saveAll(products);
    }
}

//
//    public void updateProduct(Products product) {
//        productRepository.save(product);
//    }
//
//
//    public void deleteProduct(int productId) {
//        productRepository.deleteById(productId);
//    }
