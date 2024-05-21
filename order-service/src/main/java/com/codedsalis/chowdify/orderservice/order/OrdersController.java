package com.codedsalis.chowdify.orderservice.order;

import com.codedsalis.chowdify.orderservice.order.dto.OrderDto;
import com.codedsalis.chowdify.orderservice.shared.BaseController;
import com.codedsalis.chowdify.orderservice.shared.ChowdifyResponse;
import com.codedsalis.chowdify.orderservice.shared.Statuses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController extends BaseController {

    private final OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> placeOrder(@Valid @RequestBody OrderDto orderDto) {
        orderService.createOrder(orderDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ChowdifyResponse> getOrder(@PathVariable UUID id) {
        Optional<Order> order = orderService.fetchOrder(id);

        HashMap<String, Object> data = new HashMap<>();
        data.put(order.isEmpty() ? "message" : "order", order.isEmpty() ? "The requested order is not found" : order);

        ChowdifyResponse chowdifyResponse = ChowdifyResponse.builder()
                .status(order.isEmpty() ? Statuses.error : Statuses.success)
                .statusCode(order.isEmpty() ? HttpStatus.NOT_FOUND.value() : HttpStatus.OK.value())
                .data(data)
                .build();

        return ResponseEntity.status(order.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(chowdifyResponse);

    }
}
