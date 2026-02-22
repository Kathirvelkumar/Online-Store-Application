package com.WebApplication.Service;

import com.WebApplication.Model.Products;
import com.WebApplication.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.math.*;
import java.util.*;

@Service
public class ProductServices {

    ProductRepository productRepository;

    public ProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //    Showing the entire Product
    public List<Products> getProductsList() {
        return productRepository.findAll();
    }

    //    Get a single product
    public Products getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElse(new Products(0L, "Not Found", "No Description", BigDecimal.ZERO, 0));
    }

    //    Add a single product
    public void addProduct(Products product) {
        productRepository.save(product);
    }

    //    Add bulk amount of Products
    public void addAllProducts(List<Products> products){
        productRepository.saveAll(products);
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

}
