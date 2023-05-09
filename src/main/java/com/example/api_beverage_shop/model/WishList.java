package com.example.api_beverage_shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "WishList")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne()
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "wishList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WishItem> wishItems;
}
