package com.codedsalis.chowdify.productservice.product;

import com.codedsalis.chowdify.productservice.product.dto.CreateProductDto;
import com.codedsalis.chowdify.productservice.shared.BaseController;
import com.codedsalis.chowdify.productservice.shared.ChowdifyResponse;
import com.codedsalis.chowdify.productservice.shared.Statuses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController extends BaseController {

    private final ProductService productService;

    @Autowired
    public  ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(
            path = "",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createProduct(@Valid CreateProductDto productDto) {
        productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product successfully created");
    }

    @GetMapping()
    public ResponseEntity<ChowdifyResponse> allProducts(Pageable pageable) {
        Page<Product> products = productService.fetchAll(pageable);

        HashMap<String, Page<Product>> data = new HashMap<>();
        data.put("products", products);

        ChowdifyResponse chowdifyResponse = ChowdifyResponse.builder()
                .status(Statuses.success)
                .statusCode(HttpStatus.OK.value())
                .data(data)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(chowdifyResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChowdifyResponse> getProduct(@Valid @PathVariable UUID id) {
        Optional<Product> product = productService.fetchProduct(id);

        HashMap<String, Object> data = new HashMap<>();
        data.put(product.isEmpty() ? "message" : "product", product.isEmpty() ? "The requested product is not found" : product);

        ChowdifyResponse chowdifyResponse = ChowdifyResponse.builder()
                .status(product.isEmpty() ? Statuses.error : Statuses.success)
                .statusCode(product.isEmpty() ? HttpStatus.NOT_FOUND.value() :  HttpStatus.OK.value())
                .data(data)
                .build();

        return ResponseEntity.status(product.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(chowdifyResponse);
    }
}
