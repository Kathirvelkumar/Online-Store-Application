package com.WebApplication.Service;

import com.WebApplication.Model.Orders;

import com.WebApplication.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServices {

    private final OrderRepository orderRepository;

    public OrderServices(OrderRepository orderRepository){
        this.orderRepository= orderRepository;
    }

    public List<Orders> getOrdersList(){
        return orderRepository.findAll();
    }

    public void addOrder(Orders order){
        orderRepository.save(order);
    }

}
