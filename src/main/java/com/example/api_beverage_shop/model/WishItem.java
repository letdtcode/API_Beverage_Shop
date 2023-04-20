package com.example.api_beverage_shop.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "WishItem")
public class WishItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @Column(name = "status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "wishId")
    private WishList wishList;
}
