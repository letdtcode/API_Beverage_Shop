package com.example.api_beverage_shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @ManyToMany
    @JoinTable(
            name = "cartItem_topping",
            joinColumns = {@JoinColumn(name = "cartItemId", referencedColumnName = "Id")},
            inverseJoinColumns = {@JoinColumn(name = "toppingId", referencedColumnName = "Id")}
    )
    private List<Topping> toppings;

    @ManyToOne
    @JoinColumn(name = "sizeId")
    private Size sizeProduct;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "totalPriceProduct")
    private BigDecimal totalPriceProduct;

    @Column(name = "totalPriceItem")
    private BigDecimal totalPriceItem;
}
