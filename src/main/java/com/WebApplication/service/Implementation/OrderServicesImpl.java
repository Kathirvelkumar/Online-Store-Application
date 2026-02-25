package com.WebApplication.service.Implementation;

import com.WebApplication.dto.OrderItemResponse;
import com.WebApplication.dto.OrderRequest;
import com.WebApplication.dto.OrderResponse;
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
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServicesImpl implements OrderServices {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public List<Orders> getOrdersList() {
        return orderRepository.findAll();
    }

    @Override
    public Orders getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException());
    }

    @Override
    public Orders addOrder(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }


    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {

        // 1️⃣ Check Customer exists
        Customers customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Orders order = new Orders();
        order.setCustomer(customer);
        order.setStatus(Orders.OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItems> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        // 2️⃣ Loop through items
        for (OrderRequest.ItemRequest itemReq : request.getItems()) {

            if (itemReq.getQuantity() <= 0) {
                throw new RuntimeException("Quantity must be > 0");
            }

            // 3️⃣ Check product exists
            Products product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // 4️⃣ Check stock available
            if (product.getStackQuantity() < itemReq.getQuantity()) {
                throw new RuntimeException("Not enough stock for " + product.getProductName());
            }

            // 5️⃣ Reduce stock
            product.setStackQuantity(product.getStackQuantity() - itemReq.getQuantity());
            productRepository.save(product);

            // 6️⃣ Create OrderItem
            OrderItems orderItem = new OrderItems();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemReq.getQuantity());
            orderItem.setPrice(product.getPrice());

            orderItems.add(orderItem);

            // 7️⃣ Calculate total
            total = total.add(
                    product.getPrice().multiply(BigDecimal.valueOf(itemReq.getQuantity()))
            );
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        // 🔹 Save order first
        Orders savedOrder = orderRepository.save(order);

        // 🔹 Convert to OrderResponse DTO
        OrderResponse response = new OrderResponse();
        response.setOrderId(savedOrder.getOrderId());
        response.setCustomerName(savedOrder.getCustomer().getCustomerName());
        response.setStatus(savedOrder.getStatus());
        response.setTotalAmount(savedOrder.getTotalAmount());
        response.setOrderDate(savedOrder.getOrderDate());

        // 🔹 Map OrderItems → DTO list
        List<OrderItemResponse> itemResponses = savedOrder.getItems()
                .stream()
                .map(item -> {
                    OrderItemResponse dto = new OrderItemResponse();
                    dto.setOrderItemId(item.getOrderItemsId());
                    dto.setProductName(item.getProduct().getProductName());
                    dto.setPrice(item.getPrice());
                    dto.setQuantity(item.getQuantity());
                    return dto;
                })
                .toList();

        response.setItems(itemResponses);

        return response;
    }
}