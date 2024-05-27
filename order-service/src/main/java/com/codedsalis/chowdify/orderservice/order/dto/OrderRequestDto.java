package com.codedsalis.chowdify.orderservice.order.dto;

import com.codedsalis.chowdify.orderservice.order.OrderLineItems;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequestDto {

    @Valid

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String deliveryAddress;

    @NotNull
    private List<OrderLineItemsDto> orderLineItems;
}
