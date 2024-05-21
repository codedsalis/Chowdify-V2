package com.codedsalis.chowdify.productservice.product.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record CreateProductDto (
    @Valid

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    String name,

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be a positive value")
    Integer price,

    MultipartFile image
    ){
}
