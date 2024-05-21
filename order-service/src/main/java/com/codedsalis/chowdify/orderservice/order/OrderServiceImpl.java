package com.codedsalis.chowdify.orderservice.order;

import com.codedsalis.chowdify.orderservice.order.dto.OrderDto;
import com.codedsalis.chowdify.orderservice.order.dto.OrderLineItemsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void createOrder(OrderDto orderDto) {
        Order order = new Order();

        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderDto.getOrderLineItems()
                .stream()
                        .map(this::mapToDto)
                                .toList();

        order.setOrderLineItems(orderLineItems);

        orderRepository.save(order);
    }

    @Override
    public Optional<Order> fetchOrder(UUID id) {
        return orderRepository.findById(id);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());

        return orderLineItems;
    }
}
