package com.WebApplication.service.Implementation;

import com.WebApplication.dto.ProductResponse;
import com.WebApplication.entity.Products;
import com.WebApplication.repository.ProductRepository;
import com.WebApplication.service.ProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    public List<ProductResponse> getCategoryProducts() {

        return productRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Products::getCategory))
                .entrySet()
                .stream()
                .map(e -> new ProductResponse(e.getKey(), e.getValue()))
                .toList();
    }

    @Override
    public ProductResponse getProductsByCategory(Products.ProductCategory category) {

        List<Products> filteredProducts = productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory() == category)
                .toList();

        return new ProductResponse(category, filteredProducts);
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