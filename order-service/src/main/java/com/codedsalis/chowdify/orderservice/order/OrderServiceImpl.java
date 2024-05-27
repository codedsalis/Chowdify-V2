package com.codedsalis.chowdify.orderservice.order;

import com.codedsalis.chowdify.orderservice.order.dto.OrderDto;
import com.codedsalis.chowdify.orderservice.order.dto.OrderLineItemsDto;
import com.codedsalis.chowdify.orderservice.order.dto.OrderRequestDto;
import com.codedsalis.chowdify.orderservice.order.event.OrderPlacedEvent;
import com.codedsalis.chowdify.orderservice.shared.ChowdifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, WebClient.Builder webClientBuilder, KafkaTemplate kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.webClientBuilder = webClientBuilder;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public OrderResponse<?> placeOrder(OrderDto orderDto) {
        ChowdifyResponse response = this.validateOrder(orderDto);

        log.info(response.toString());
        List<?> orderLineItems = (List<?>) response.getData().get("unAvailable");

        if (orderLineItems.isEmpty()) {
            Order order = this.createOrder(orderDto);
            return new OrderResponse<>(order);
        }

        return new OrderResponse<>(orderLineItems);
    }

    public ChowdifyResponse validateOrder(OrderDto orderDto) {
        ChowdifyResponse result = webClientBuilder.build()
                .post()
                .uri("http://inventory-service/api/v1/inventory")
                .bodyValue(orderDto)
                .retrieve()
                .bodyToMono(ChowdifyResponse.class)
                .block();

        return result;
    }

    @Override
    public Order createOrder(OrderDto orderDto) {
        Order order = new Order();

        order.setOrderNumber(UUID.randomUUID().toString());
        order.setName(orderDto.getName());
        order.setEmail(orderDto.getEmail());
        order.setDeliveryAddress(orderDto.getDeliveryAddress());

        List<OrderLineItems> orderLineItems = orderDto.getOrderLineItems()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItems(orderLineItems);

        orderRepository.save(order);
//        kafkaTemplate.send(Topics.ORDER_PLACED.toString(), new OrderPlacedEvent(order));

        return order;
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
