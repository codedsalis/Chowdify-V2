package com.codedsalis.chowdify.orderservice.order;

import com.codedsalis.chowdify.orderservice.order.dto.OrderDto;
import com.codedsalis.chowdify.orderservice.order.dto.OrderRequestDto;

import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    Order createOrder(OrderDto orderDto);

    Optional<Order> fetchOrder(UUID id);

    OrderResponse<?> placeOrder(OrderDto orderDto);
}
