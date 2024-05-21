package com.codedsalis.chowdify.orderservice.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public final class OrderDto {
    private final @Valid String orderNumber;
    private final @NotNull List<OrderLineItemsDto> orderLineItems;

}
