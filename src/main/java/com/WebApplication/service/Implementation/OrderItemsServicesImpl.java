package com.WebApplication.service.Implementation;

import com.WebApplication.dto.ProductResponse2;
import com.WebApplication.entity.Products;
import com.WebApplication.repository.OrderItemsRepository;
import com.WebApplication.repository.ProductRepository;
import com.WebApplication.service.OrderItemsServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemsServicesImpl implements OrderItemsServices {

    @Autowired
    private final OrderItemsRepository orderItemsRepository;
    private final ProductRepository productRepository;


    @Override
    public List<ProductResponse2> top3SoldProducts() {
        List<Object[]> result = orderItemsRepository.getTop3SoldProducts();

        return result.stream().map(obj -> {

            Long productId = ((Number) obj[0]).longValue();
//            Long totalSold = ((Number) obj[1]).longValue();

            Products product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            ProductResponse2 response = new ProductResponse2();

            response.setProductId(product.getProductId());
            response.setProductName(product.getProductName());
            response.setDescription(product.getDescription());
            response.setCategory(product.getCategory());
            response.setPrice(product.getPrice());
            response.setStackQuantity(product.getStackQuantity());

            return response;

        }).toList();
    }
}
