package com.codedsalis.chowdify.orderservice.order;

import com.codedsalis.chowdify.orderservice.shared.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "order_line_items")
public class OrderLineItems extends BaseModel {

    @Column(nullable = false)
    private String skuCode;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer quantity;

}
