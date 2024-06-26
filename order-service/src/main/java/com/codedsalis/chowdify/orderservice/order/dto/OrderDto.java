package com.codedsalis.chowdify.orderservice.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public final class OrderDto {
    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String deliveryAddress;

    @Valid
    private final String orderNumber;

    @NotNull
    private final List<OrderLineItemsDto> orderLineItems;

}
