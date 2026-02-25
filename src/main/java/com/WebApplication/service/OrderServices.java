package com.WebApplication.service;

import com.WebApplication.dto.OrderRequest;
import com.WebApplication.dto.OrderResponse;
import com.WebApplication.entity.Orders;
import java.util.List;

public interface OrderServices {

    List<Orders> getOrdersList();

    OrderResponse getOrderById(Long orderId);

    Orders addOrder(Orders order);

    void deleteOrder(Long orderId);

    OrderResponse placeOrder(OrderRequest request);
}