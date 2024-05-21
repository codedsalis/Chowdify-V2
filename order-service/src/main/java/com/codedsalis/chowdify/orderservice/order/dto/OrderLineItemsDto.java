package com.codedsalis.chowdify.orderservice.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderLineItemsDto {

    @Valid

    private UUID id;

    @NotNull
    private String skuCode;

    @NotNull
    @Positive
    private Integer price;

    @Positive
    @NotNull
    private Integer quantity;
}
