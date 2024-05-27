package com.codedsalis.chowdify.inventoryservice.inventory.dto;

import com.codedsalis.chowdify.inventoryservice.inventory.OrderLineItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class OrderLineItemDto {

    @Valid

    @NotNull
    @NotBlank
    private List<OrderLineItem> orderLineItems;
}
