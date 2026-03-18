package com.WebApplication.service;


import com.WebApplication.dto.ProductResponse2;
import java.util.List;

public interface OrderItemsServices {

    List<ProductResponse2> top3SoldProducts();
}
