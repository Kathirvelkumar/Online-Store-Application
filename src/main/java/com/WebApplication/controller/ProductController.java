package com.WebApplication.controller;

import com.WebApplication.dto.ProductResponse;
import com.WebApplication.dto.ProductResponse2;
import com.WebApplication.entity.Products;
import com.WebApplication.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    // GET: Fetch all products
    @GetMapping()
    ResponseEntity<List<Products>> getProducts() {
        List<Products> products = productServices.getProductsList();

//      If Products is Empty
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.status(200).body(products);
    }

    // GET: Fetch product by ID
    @GetMapping("/{productId}")
    ResponseEntity<Products> getProductById(@PathVariable Long productId) {
        Products product = productServices.getProductById(productId);
        return ResponseEntity.status(200).body(product);
    }

    // POST: Add a single product
    @PostMapping("/create-product")
    ResponseEntity<Products> addProduct(@RequestBody Products product) {
        Products newproduct = productServices.addProduct(product);
        return ResponseEntity.status(201).body(newproduct);
    }

    // POST: Add multiple products
    @PostMapping("/bulk")
    ResponseEntity<List<Products>> addAllProducts(@RequestBody List<Products> productsList) {
        List<Products> products = productServices.addAllProducts(productsList);
        return ResponseEntity.status(201).body(products);
    }

    @GetMapping("/category")
    ResponseEntity<List<ProductResponse>> getCategoryProducts() {
        List<ProductResponse> productResponse = productServices.getCategoryProducts();
        return ResponseEntity.status(200).body(productResponse);
    }

    @GetMapping("/category/{categoryName}")
    ResponseEntity<ProductResponse> getProductsByCategory(@PathVariable Products.ProductCategory categoryName){
        ProductResponse productResponse = productServices.getProductsByCategory(categoryName);
        return ResponseEntity.ok(productResponse);
    }
    @DeleteMapping("/delete/{productId}")
    ResponseEntity<ProductResponse2> deleteProductById(@PathVariable Long productId) {
        ProductResponse2 productResponse = productServices.deleteProductById(productId);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/sortedByName")
    ResponseEntity<List<ProductResponse2>> getSortedProductsByName(){
        List<ProductResponse2> productResponses = productServices.getSortedProductsByName();
        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/sortedByPrice")
    ResponseEntity<List<ProductResponse2>> getSortedProductsByPrice(){
        List<ProductResponse2> productResponses = productServices.getSortedProductsByPrice();
        return ResponseEntity.ok(productResponses);
    }

    @PostMapping("/filter/Greater")
    ResponseEntity<List<ProductResponse2>> filterGreater(@RequestParam BigDecimal price){
        List<ProductResponse2> productResponse = productServices.filterGreater(price);
        return ResponseEntity.ok(productResponse);
    }

    @PostMapping("/filter/Smaller")
    ResponseEntity<List<ProductResponse2>> filterSmaller(@RequestParam BigDecimal price){
        List<ProductResponse2> productResponse = productServices.filterSmaller(price);
        return ResponseEntity.ok(productResponse);
    }

}
