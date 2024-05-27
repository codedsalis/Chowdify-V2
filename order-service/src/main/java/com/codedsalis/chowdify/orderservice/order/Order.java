package com.codedsalis.chowdify.orderservice.order;

import com.codedsalis.chowdify.orderservice.shared.BaseModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "orders")
public class Order extends BaseModel {

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItems;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String deliveryAddress;

}
