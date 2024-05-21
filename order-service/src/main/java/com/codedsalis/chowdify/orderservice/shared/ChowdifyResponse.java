package com.codedsalis.chowdify.orderservice.shared;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Data
@Builder
public class ChowdifyResponse {
    private Statuses status;

    private Integer statusCode;

    private HashMap<String, ?> data;
}

