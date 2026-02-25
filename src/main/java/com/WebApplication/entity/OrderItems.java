package com.WebApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderItemsId;
    private int quantity;
    private BigDecimal price;

//  FK -> from the Product_Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

//  FK -> from the Order_Id
    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Orders order;

}
