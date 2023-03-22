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
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @Column(name = "userId")
    private int userId;
}
