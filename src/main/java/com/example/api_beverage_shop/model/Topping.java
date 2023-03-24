package com.example.api_beverage_shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "Topping")
public class Topping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int toppingId;

    @Column(name = "toppingName")
    private String toppingName;

    @Column(name = "toppingPrice")
    private BigDecimal toppingPrice;
}
