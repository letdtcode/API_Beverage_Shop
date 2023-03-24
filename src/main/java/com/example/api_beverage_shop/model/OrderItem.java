package com.example.api_beverage_shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "OrderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "orderItem_topping",
            joinColumns = {@JoinColumn(name = "orderItemId", referencedColumnName = "orderItemId")},
            inverseJoinColumns = {@JoinColumn(name = "toppingId", referencedColumnName = "toppingId")}
    )
    private List<Topping> toppings;

    @ManyToOne
    @JoinColumn(name = "sizeId")
    private Size sizeProduct;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "totalPriceProduct")
    private BigDecimal totalPriceProduct;

    @Column(name = "totalPriceItem")
    private BigDecimal totalPriceItem;
}
