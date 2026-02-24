package com.WebApplication.service.Implementation;

import com.WebApplication.entity.Products;
import com.WebApplication.repository.ProductRepository;
import com.WebApplication.service.ProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServicesImpl implements ProductServices {

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public Products addProduct(Products product) {
        return productRepository.save(product);
    }

    @Override
    public List<Products> addAllProducts(List<Products> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public List<Products> getProductsList() {
        return productRepository.findAll();
    }

    @Override
    public Products getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product Not Found"));
    }
}