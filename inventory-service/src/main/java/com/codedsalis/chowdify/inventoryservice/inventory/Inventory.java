package com.codedsalis.chowdify.inventoryservice.inventory;

import com.codedsalis.chowdify.inventoryservice.shared.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Inventory extends BaseModel {

    @Column(nullable = false)
    private String skuCode;

    @Column(nullable = false)
    private Long quantityAvailable;
}
