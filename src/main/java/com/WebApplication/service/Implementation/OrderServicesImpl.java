package com.WebApplication.service.Implementation;

import com.WebApplication.dto.*;
import com.WebApplication.entity.Customers;
import com.WebApplication.entity.OrderItems;
import com.WebApplication.entity.Orders;
import com.WebApplication.entity.Products;
import com.WebApplication.repository.CustomerRepository;
import com.WebApplication.repository.OrderRepository;
import com.WebApplication.repository.ProductRepository;
import com.WebApplication.service.OrderServices;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServicesImpl implements OrderServices {

    @Autowired
    private ModelMapper mapper;

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public List<OrderResponse> getOrdersList() {
        return orderRepository.findAll().stream().map(order -> mapper.map(order, OrderResponse.class)).toList();
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order Id doesn't exist"));
        return mapper.map(order, OrderResponse.class);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {
        // Check Customer present in DB
        Customers customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Orders order = new Orders();
        order.setCustomer(customer);
        order.setStatus(Orders.OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItems> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrderRequest.ItemRequest itemReq : request.getItems()) {

            if (itemReq.getQuantity() <= 0) {
                throw new RuntimeException("Quantity must be > 0");
            }

            // Check product present in DB
            Products product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            //  Check stock present or not
            if (product.getStackQuantity() < itemReq.getQuantity()) {
                throw new RuntimeException("Not enough stock for " + product.getProductName());
            }

            //  Reduce stock after selecting the order stock
            product.setStackQuantity(product.getStackQuantity() - itemReq.getQuantity());
            productRepository.save(product);

            //  Create OrderItem
            OrderItems orderItem = new OrderItems();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemReq.getQuantity());
            orderItem.setPrice(product.getPrice());

            orderItems.add(orderItem);

            // Calculate total
            total = total.add(
                    product.getPrice().multiply(BigDecimal.valueOf(itemReq.getQuantity()))
            );
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        // Save order first
        Orders savedOrder = orderRepository.save(order);

        // Convert to OrderResponse DTO
        OrderResponse response = new OrderResponse();
        response.setOrderId(savedOrder.getOrderId());
        response.setCustomerName(savedOrder.getCustomer().getCustomerName());
        response.setStatus(savedOrder.getStatus());
        response.setTotalAmount(savedOrder.getTotalAmount());
        response.setOrderDate(savedOrder.getOrderDate());

        // Map OrderItems → DTO list
        List<OrderItemResponse> itemResponses = savedOrder.getItems()
                .stream()
                .map(item -> {
                    OrderItemResponse dto = new OrderItemResponse();
                    dto.setOrderItemsId(item.getOrderItemsId());
                    dto.setProductName(item.getProduct().getProductName());
                    dto.setPrice(item.getPrice());
                    dto.setQuantity(item.getQuantity());
                    return dto;
                })
                .toList();

        response.setItems(itemResponses);

        return response;
    }


    @Transactional
    public OrderResponse cancelOrderById(Long orderId) {
//      Check Order present in DB
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order doesn't exist"));

        if (order.getStatus() == Orders.OrderStatus.DELIVERED) {
            throw new RuntimeException("Order Already Delivered");
        }

        order.setStatus(Orders.OrderStatus.CANCELLED);
        for (OrderItems item : order.getItems()) {
            Products product = item.getProduct();
            product.setStackQuantity(product.getStackQuantity() + item.getQuantity());
            productRepository.save(product);
        }
        Orders updatedOrder = orderRepository.save(order);

        return mapper.map(updatedOrder, OrderResponse.class);

    }

    @Override
    public List<CustomerResponse> getTop3Customers() {
        List<Orders> orders = orderRepository.findAll();

        List<Customers> top3 = orders.stream()
                .sorted(Comparator.comparing(Orders::getTotalAmount).reversed()).limit(3)
                .map(Orders::getCustomer).toList();

        return top3.stream().map(m -> mapper.map(m, CustomerResponse.class)).toList();
    }

    //  More than N Orders
    @Override
    public List<CustomerResponse> getMoreThenNorder(long orderNumbers) {
        List<Orders> orders = orderRepository.findAll();
        List<CustomerResponse> customerResponses = new ArrayList<>();

        Map<Customers, List<Orders>> map = orders.stream()
                .collect(Collectors.groupingBy(Orders::getCustomer));

        for (Map.Entry<Customers, List<Orders>> entrySet : map.entrySet()) {
            Customers customer = entrySet.getKey();
            List<Orders> order = entrySet.getValue();

            if (order.size() > orderNumbers)
                customerResponses.add(mapper.map(customer, CustomerResponse.class));
        }

        return customerResponses;


//        return orderRepository.findAll().stream()
//                .collect(Collectors.groupingBy(Orders::getCustomer))
//                .entrySet().stream()
//                .filter(entry -> entry.getValue().size() > orderNumbers)
//                .map(entry -> mapper.map(entry.getKey(), CustomerResponse.class))
//                .toList();
    }

    @Override
    public Map<CustomerResponse, BigDecimal> totalRevenuePerCustomer() {

        return orderRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        Orders::getCustomer,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Orders::getTotalAmount,
                                BigDecimal::add
                        )
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> mapper.map(entry.getKey(), CustomerResponse.class),
                        Map.Entry::getValue
                ));
    }

    @Override
    public List<OrderResponse> getTop5Orders(){

        return orderRepository.findAll().stream()
                .sorted(Comparator.comparing(Orders::getTotalAmount).reversed())
                .limit(5)
                .map(o -> mapper.map(o, OrderResponse.class)).toList();

    }

    @Override
    public List<Orders> getLast7DaysOrders() {
        LocalDateTime last7Days = LocalDateTime.now().minusDays(7);
        return orderRepository.getOrdersLast7Days(last7Days);
    }

}