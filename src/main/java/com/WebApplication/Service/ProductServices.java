package com.WebApplication.Service;

import com.WebApplication.Model.Products;
import com.WebApplication.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServices {

    @Autowired
    ProductRepository productRepository;

//    //    Showing the entire Product
//    public List<Products> getProductsList() {
//        return productRepository.findAll();
//    }
//
//    //    Get a single product
//    public Products getProductById(int productId) {
//        return productRepository.findById(productId).orElse(new Products(0, "Not Found", 0));
//    }
//
//    //    Add a single product
//    public void addProduct(Products product) {
//        productRepository.save(product);
//    }
//
//    public void addAllProducts(List<Products> products){
//        productRepository.saveAll(products);
//    }
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
