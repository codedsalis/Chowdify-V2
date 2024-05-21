package com.codedsalis.chowdify.orderservice.shared;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

public class BaseController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        HashMap<String, String> messages = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(message ->
                messages.put(message.getField(), message.getDefaultMessage()));

        HashMap<String, Object> errors = new HashMap<>();
        errors.put("errors", messages);

        ChowdifyResponse chowdifyResponse = ChowdifyResponse.builder()
                .status(Statuses.error)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .data(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(chowdifyResponse);
    }
}
