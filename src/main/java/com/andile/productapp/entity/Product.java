package com.andile.productapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Product {
    @Id
    private String id;
    private String name;
    private int qty;
    private double price;

    public Product(String name, int qty, double price) {
        this.name = name;
        this.price = price;
        this.qty = qty;
    }
}
