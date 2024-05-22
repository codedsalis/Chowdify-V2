package com.codedsalis.chowdify.inventoryservice.inventory;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Boolean isInStock(String skuCode) {
        return inventoryRepository.existsBySkuCode(skuCode);
    }
}
