package com.codedsalis.chowdify.orderservice.order;

import com.codedsalis.chowdify.orderservice.order.dto.OrderDto;
import com.codedsalis.chowdify.orderservice.order.dto.OrderRequestDto;
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
    public ResponseEntity<ChowdifyResponse> placeOrder(@Valid @RequestBody OrderDto orderDto) {
        var orderResponse = orderService.placeOrder(orderDto);

        HashMap<String, Object> data = new HashMap<>();
        data.put(orderResponse.getData() instanceof Order ? "order" : "unAvailable", orderResponse.getData());

        ChowdifyResponse chowdifyResponse = ChowdifyResponse.builder()
                .status(orderResponse.getData() instanceof Order ? Statuses.success : Statuses.error)
                .statusCode(orderResponse.getData() instanceof Order ? HttpStatus.CREATED.value() : HttpStatus.UNPROCESSABLE_ENTITY.value())
                .data(data)
                .build();

        return ResponseEntity.status(orderResponse.getData() instanceof Order ? HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY)
                .body(chowdifyResponse);
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
