package com.WebApplication.Controller;

import com.WebApplication.Model.Orders;
import com.WebApplication.Service.OrderServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class OrderController {

    OrderServices orderServices;

    public OrderController(OrderServices orderServices){
        this.orderServices = orderServices;
    }
    @GetMapping("/api/orders")
    public List<Orders> getOrdersList(){
        return orderServices.getOrdersList();
    }

    @PostMapping("/api/orders")
    public void addOrder(Orders orders){
        orderServices.addOrder(orders);
    }
}
