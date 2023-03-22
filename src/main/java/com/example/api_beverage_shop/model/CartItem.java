package com.example.api_beverage_shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "topping")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;

    @Column(name = "userId")
    private int productId;

    @Column(name = "userId")
    private int cartId;

//    @Column(name = "userId")
    private List<Topping> listTopping;
    private int sizeId;

    @Column(name = "userId")
    private int quantity;

    @Column(name = "userId")
    private int totalPrice;
}
