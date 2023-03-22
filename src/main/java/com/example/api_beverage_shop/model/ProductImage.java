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
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productImageId;

    @Column(name = "productId")
    private int productId;

    @Column(name = "image")
    private String image;
}
