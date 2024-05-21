package com.codedsalis.chowdify.orderservice.shared;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@MappedSuperclass
public class BaseModel implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid7")
    protected UUID id;

    @CreationTimestamp
    protected Timestamp createdAt;

    @UpdateTimestamp
    protected Timestamp updatedAt;
}


