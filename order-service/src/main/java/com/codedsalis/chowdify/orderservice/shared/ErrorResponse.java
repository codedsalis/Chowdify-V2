package com.codedsalis.chowdify.orderservice.shared;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String status;
    private int statusCode;
    private Data data;

    public ErrorResponse(String status, int statusCode, String message) {
        this.status = status;
        this.statusCode = statusCode;
        this.data = new Data(message);
    }


    @Setter
    @Getter
    public static class Data {
        private String message;

        public Data(String message) {
            this.message = message;
        }

    }
}
