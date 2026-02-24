package com.WebApplication.service;

import com.WebApplication.entity.Orders;
import java.util.List;

public interface OrderServices {

    List<Orders> getOrdersList();

    Orders getOrderById(Long orderId);

    Orders addOrder(Orders order);

    void deleteOrder(Long orderId);
}