package com.WebApplication.service.Implementation;

import com.WebApplication.entity.Orders;
import com.WebApplication.repository.OrderRepository;
import com.WebApplication.service.OrderServices;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServicesImpl implements OrderServices {

    private final OrderRepository orderRepository;

    public OrderServicesImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Orders> getOrdersList() {
        return orderRepository.findAll();
    }

    @Override
    public Orders getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElse(new Orders(
                        0L,
                        LocalDateTime.now(),
                        Orders.OrderStatus.NO_ORDER,
                        BigDecimal.ZERO
                ));
    }

    @Override
    public Orders addOrder(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}