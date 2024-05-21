package com.codedsalis.chowdify.inventoryservice.inventory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public Boolean isInstock(String skuCode) {
        return inventoryRepository.existsBySkuCode(skuCode);
    }
}
