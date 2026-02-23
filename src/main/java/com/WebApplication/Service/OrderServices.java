package com.WebApplication.Service;

import com.WebApplication.Model.Orders;

import com.WebApplication.Repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServices {

    private final OrderRepository orderRepository;

    public OrderServices(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Orders> getOrdersList() {
        return orderRepository.findAll();
    }

    public Orders getOrderById(Long orderId){
        return orderRepository.findById(orderId)
                .orElse(new Orders(
                        0L,
                        LocalDateTime.now(),
                        Orders.OrderStatus.NO_ORDER,
                        BigDecimal.ZERO
                ));
    }

    public void addOrder(Orders order) {
        orderRepository.save(order);
    }

    public void deleteOrder(Long orderId){
        orderRepository.deleteById(orderId);
    }

}
