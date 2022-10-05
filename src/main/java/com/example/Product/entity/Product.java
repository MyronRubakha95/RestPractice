package com.example.Product.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private BigDecimal price;

    @Version
    private Long version;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}