package com.WebApplication.Controller;

import com.WebApplication.Model.Products;
import com.WebApplication.Service.ProductServices;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController {
    ProductServices productServices;

    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping("/api/products")
    public List<Products> getProducts() {
        return productServices.getProductsList();
    }

    @GetMapping("/api/products/{productId}")
    public Products getProductById(@PathVariable Long productId) {
        return productServices.getProductById(productId);
    }

    @PostMapping("/api/products")
    public void addProduct(@RequestBody Products product) {
        productServices.addProduct(product);
    }

    @PostMapping("/api/products/bulk")
    public void addProducts(@RequestBody List<Products> products) {
        productServices.addAllProducts(products);
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
}
