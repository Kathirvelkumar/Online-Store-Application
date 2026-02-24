package com.WebApplication.controller;

import com.WebApplication.entity.Products;
import com.WebApplication.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    // GET: Fetch all products
    @GetMapping()
    ResponseEntity<List<Products>> getProducts(){
        List<Products> products = productServices.getProductsList();

//      If Products is Empty
        if(products.isEmpty()){
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.status(200).body(products);
    }

    // GET: Fetch product by ID
    @GetMapping("/{productId}")
    ResponseEntity<Products> getProductById(@PathVariable Long productId){
        Products product = productServices.getProductById(productId);
        return ResponseEntity.status(200).body(product);
    }

    // POST: Add a single product
    @PostMapping
    ResponseEntity<Products> addProduct(@RequestBody Products product){
        Products newproduct = productServices.addProduct(product);
        return ResponseEntity.status(201).body(newproduct);
    }

    // POST: Add multiple products
    @PostMapping("/bulk")
    ResponseEntity<List<Products>> addAllProducts(@RequestBody List<Products> productsList){
        List<Products> products = productServices.addAllProducts(productsList);
        return ResponseEntity.status(201).body(products);
    }
}