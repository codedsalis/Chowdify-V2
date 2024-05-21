package com.codedsalis.chowdify.productservice.product;

import com.codedsalis.chowdify.productservice.file.FileUploadService;
import com.codedsalis.chowdify.productservice.product.dto.CreateProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final FileUploadService fileUploadService;

    @Autowired
    public ProductServiceImpl(FileUploadService fileUploadService, ProductRepository productRepository1) {
        this.productRepository = productRepository1;
        this.fileUploadService = fileUploadService;
    }

    @Override
    public void createProduct(CreateProductDto productDto) {
        var image = new HashMap<String, Object>();

        if (productDto.image() != null) {
            var fileUpload = fileUploadService.upload(productDto.image());

            image.put("url", fileUpload.status() ? fileUpload.data().get("url") : null);
            image.put("public_id", fileUpload.status() ? fileUpload.data().get("public_id") : null);
        }

        Product product = Product.builder()
                .name(productDto.name())
                .price(productDto.price())
                .images(image)
                .skuCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                .build();

        this.productRepository.save(product);
    }

    @Override
    public Page<Product> fetchAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> fetchProduct(UUID id) {
        return productRepository.findById(id);
    }
}
