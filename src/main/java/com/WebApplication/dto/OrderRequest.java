package com.WebApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long customerId;
    private List<ItemRequest> items;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItemRequest {
        private Long productId;
        private int quantity;
    }
}
