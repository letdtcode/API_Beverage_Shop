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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "userId")
    private String nameCustomer;

    @Column(name = "userId")
    private String phoneNumber;

    @Column(name = "userId")
    private String address;

    @Column(name = "userId")
    private int shipping;

    @Column(name = "userId")
    private int totalItemPrice;

    @Column(name = "userId")
    private int payment;

    @Column(name = "userId")
    private int discountId;

    @Column(name = "userId")
    private int totalPrice;

    @Column(name = "userId")
    private List<OrderItem> listOrderItem;

    @Column(name = "userId")
    private int userId;
}
