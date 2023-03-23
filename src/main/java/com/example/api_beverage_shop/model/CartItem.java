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
@Table(name = "cartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @ManyToMany
    @JoinTable(
            name = "cartItem_topping",
            joinColumns = {@JoinColumn(name = "cartItemId", referencedColumnName = "cartItemId")},
            inverseJoinColumns = {@JoinColumn(name = "toppingId", referencedColumnName = "toppingId")}
    )
    private List<Topping> toppings;

    @ManyToOne
    @JoinColumn(name = "sizeId")
    private Size sizeProduct;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "totalPrice")
    private int totalPrice;
}
