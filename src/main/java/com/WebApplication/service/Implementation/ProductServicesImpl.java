package com.WebApplication.service.Implementation;

import com.WebApplication.dto.ProductResponse;
import com.WebApplication.dto.ProductResponse2;
import com.WebApplication.entity.Products;
import com.WebApplication.repository.ProductRepository;
import com.WebApplication.service.ProductServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServicesImpl implements ProductServices {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;

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
    public ProductResponse2 deleteProductById(Long productId) {
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product Not Found with ID: " + productId));

        ProductResponse2 productResponse = mapper.map(product,ProductResponse2.class);

        productRepository.deleteById(productId);

        return productResponse;
    }

    @Override
    public List<Products> getProductsList() {
        return productRepository.findAll();
    }

    @Override
    public Products getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product Not Found"));
    }

    @Override
    public List<ProductResponse2> getSortedProductsByName(){
        List<Products> products = productRepository.findAll();
        products.sort(Comparator.comparing(Products::getProductName).reversed());

          return products.stream()
                  .map(p-> mapper.map(p,ProductResponse2.class)).toList();
    }

    @Override
    public List<ProductResponse2> getSortedProductsByPrice(){
        List<Products> products = productRepository.findAll();
        products.sort(Comparator.comparing(Products::getPrice));

        return products.stream().map(p-> mapper.map(p,ProductResponse2.class)).toList();
    }

    @Override
    public List<ProductResponse2> filterGreater(BigDecimal price) {
        List<Products> products = productRepository.findAll();

        return products.stream()
                .filter(p -> p.getPrice().compareTo(price) > 0)
                .map(p -> mapper.map(p, ProductResponse2.class))
                .toList();
    }

    @Override
    public List<ProductResponse2> filterSmaller(BigDecimal price) {
        List<Products> products = productRepository.findAll();

        return products.stream()
                .filter(p -> p.getPrice().compareTo(price) < 0)
                .map(p -> mapper.map(p, ProductResponse2.class))
                .toList();
    }

    @Override
    public List<ProductResponse2> findTop5Products() {
        List<Products> products = productRepository.top5Products();

        return products.stream().map(m -> mapper.map(m,ProductResponse2.class)).toList();
    }

}