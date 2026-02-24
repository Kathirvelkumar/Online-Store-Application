package com.WebApplication.service.Implementation;

import com.WebApplication.entity.Products;
import com.WebApplication.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServicesImpl implements com.WebApplication.service.ProductServices {

    private final ProductRepository productRepository;

    public ProductServicesImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Products> getProductsList() {
        return productRepository.findAll();
    }

    @Override
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

    @Override
    public Products addProduct(Products product) {
        return productRepository.save(product);
    }

    @Override
    public List<Products> addAllProducts(List<Products> products) {
        return productRepository.saveAll(products);
    }
}