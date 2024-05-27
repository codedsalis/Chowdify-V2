package com.codedsalis.chowdify.productservice.product;

import com.codedsalis.chowdify.productservice.shared.BaseModel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Product extends BaseModel {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(nullable = false)
    private Map<String, Object> images;

    @Column(nullable = false, unique = true)
    private String skuCode;

}
