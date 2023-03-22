package com.example.api_beverage_shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "topping")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "priceDefault")
    private int priceDefault;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;
}
