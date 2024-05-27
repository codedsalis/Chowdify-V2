package com.codedsalis.chowdify.orderservice.order.event;

import com.codedsalis.chowdify.orderservice.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent {
    private Order order;
}
