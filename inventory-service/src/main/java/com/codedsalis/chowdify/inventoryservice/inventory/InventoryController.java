package com.codedsalis.chowdify.inventoryservice.inventory;

import com.codedsalis.chowdify.inventoryservice.shared.ChowdifyResponse;
import com.codedsalis.chowdify.inventoryservice.shared.Statuses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController()
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ChowdifyResponse> checkInStock(@PathVariable String skuCode) {
        Boolean isInStock = inventoryService.isInStock(skuCode);

        HashMap<String, Object> res = new HashMap<>();
        res.put("inStock", isInStock);

        ChowdifyResponse chowdifyResponse = ChowdifyResponse.builder()
                .status(Statuses.success)
                .statusCode(HttpStatus.OK.value())
                .data(res)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(chowdifyResponse);
    }
}
