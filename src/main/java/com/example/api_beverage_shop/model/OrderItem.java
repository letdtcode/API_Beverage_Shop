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
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemId;

    @Column(name = "productId")
    private int productId;

    @Column(name = "orderId")
    private int orderId;

//    @Column(name = "listTopping")
    private List<Topping> listTopping;
}
