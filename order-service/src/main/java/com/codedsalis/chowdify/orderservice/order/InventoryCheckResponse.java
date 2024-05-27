package com.codedsalis.chowdify.orderservice.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class InventoryCheckResponse {
    private List<OrderLineItem> orderLineItems;
}

class OrderLineItem {
    private String skuCode;

    private Integer price;

    private Integer quantity;
}