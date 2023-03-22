package com.example.api_beverage_shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "topping")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int discountId;

    @Column(name = "userId")
    private String discountCode;

    @Column(name = "userId")
    private String imageDemo;

    @Column(name = "userId")
    private LocalDate dateStart;

    @Column(name = "userId")
    private LocalDate dateEnd;

    @Column(name = "userId")
    private int discountValue;

    @Column(name = "userId")
    private String description;

    @Column(name = "userId")
    private int quantity;
}
