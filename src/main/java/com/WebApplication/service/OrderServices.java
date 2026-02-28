package com.WebApplication.service;

import com.WebApplication.dto.CustomerResponse;
import com.WebApplication.dto.OrderRequest;
import com.WebApplication.dto.OrderResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderServices {

    List<OrderResponse> getOrdersList();

    OrderResponse getOrderById(Long orderId);

    void deleteOrder(Long orderId);

    OrderResponse placeOrder(OrderRequest request);

    OrderResponse cancelOrderById(Long orderId);

    List<CustomerResponse> getTop3Customers();

    List<CustomerResponse> getMoreThenNorder(long orderNumbers);

    Map<CustomerResponse, BigDecimal> totalRevenuePerCustomer();

    List<OrderResponse> getTop5Orders();
}