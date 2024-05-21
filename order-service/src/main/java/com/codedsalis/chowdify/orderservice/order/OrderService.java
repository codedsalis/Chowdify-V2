package com.codedsalis.chowdify.orderservice.order;

import com.codedsalis.chowdify.orderservice.order.dto.OrderDto;

import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    void createOrder(OrderDto orderDto);

    Optional<Order> fetchOrder(UUID id);
}
