package com.codedsalis.chowdify.productservice.product;

import com.codedsalis.chowdify.productservice.product.dto.CreateProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    void createProduct(CreateProductDto productDto);

    Page<Product> fetchAll(Pageable pageable);

    Optional<Product> fetchProduct(UUID id);
}
