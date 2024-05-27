package com.codedsalis.chowdify.inventoryservice.inventory;

import com.codedsalis.chowdify.inventoryservice.inventory.dto.OrderLineItemDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Boolean isInStock(String skuCode, Integer quantityOrdered) {
        Optional<Inventory> inventory = inventoryRepository.findBySkuCode(skuCode);

        return inventory.isPresent() && inventory.get().getQuantityAvailable() > quantityOrdered;
    }

    private void reserveStockForOrder(String skuCode, Integer quantity) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode).get();

        inventory.setQuantityAvailable(inventory.getQuantityAvailable() - quantity);
        inventoryRepository.save(inventory);
    }

    public OrderLineItem[] checkInventory(OrderLineItemDto orderLineItemDto) {

        List<OrderLineItem> unAvailable = new ArrayList<>();

        List<String> skuCodes = new ArrayList<>(orderLineItemDto.getOrderLineItems()
                .stream()
                .map(OrderLineItem::getSkuCode)
                .toList());

        List<Inventory> inventories = inventoryRepository.findBySkuCodeIn(skuCodes);

        for (OrderLineItem item : orderLineItemDto.getOrderLineItems()) {
            Optional<Inventory> product = inventories.stream()
                    .filter(inventory -> item.getSkuCode().equals(inventory.getSkuCode()))
                    .findFirst();

            if (product.isPresent()) {
                if (product.get().getQuantityAvailable() < item.getQuantity()) {
                    unAvailable.add(item);
                } else {
                    this.reserveStockForOrder(product.get().getSkuCode(), item.getQuantity());
                }
            } else {
                unAvailable.add(item);
            }
        }

        return unAvailable.toArray(new OrderLineItem[0]);
    }

}
