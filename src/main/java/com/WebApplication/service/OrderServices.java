package com.WebApplication.service;

import com.WebApplication.dto.CustomerResponse;
import com.WebApplication.dto.OrderRequest;
import com.WebApplication.dto.OrderResponse;
import java.util.List;

public interface OrderServices {

    List<OrderResponse> getOrdersList();

    OrderResponse getOrderById(Long orderId);

    void deleteOrder(Long orderId);

    OrderResponse placeOrder(OrderRequest request);

    OrderResponse cancelOrderById(Long orderId);

    List<CustomerResponse> getTop3Customers();

    List<CustomerResponse> getMoreThenNorder(long orderNumbers);
}