package com.codedsalis.chowdify.inventoryservice.inventory;

import com.codedsalis.chowdify.inventoryservice.inventory.dto.OrderLineItemDto;
import com.codedsalis.chowdify.inventoryservice.shared.ChowdifyResponse;
import com.codedsalis.chowdify.inventoryservice.shared.Statuses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController()
@RequestMapping("/api/v1/inventory")
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ChowdifyResponse> checkInventory(@Valid @RequestBody OrderLineItemDto orderLineItemDto) {
        log.info(orderLineItemDto.toString());
        OrderLineItem[] unAvailableStocks = inventoryService.checkInventory(orderLineItemDto);

        HashMap<String, Object> response = new HashMap<>();
        response.put("unAvailable", unAvailableStocks);

        ChowdifyResponse chowdifyResponse = ChowdifyResponse.builder()
                .status(Statuses.success)
                .statusCode(HttpStatus.OK.value())
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(chowdifyResponse);
    }
}
