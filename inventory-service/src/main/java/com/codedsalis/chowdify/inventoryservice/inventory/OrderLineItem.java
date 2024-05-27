package com.codedsalis.chowdify.inventoryservice.inventory;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class OrderLineItem {

    @Valid

    @NotNull
    private String skuCode;

    @NotNull
    @Positive
    private Integer price;

    @NotNull
    @Positive
    private Integer quantity;
}
