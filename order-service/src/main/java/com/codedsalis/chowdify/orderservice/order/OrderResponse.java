package com.codedsalis.chowdify.orderservice.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse<T> {
    private T data;
}