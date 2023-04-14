package com.example.api_beverage_shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Topping")
public class Topping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "toppingName", columnDefinition = "nvarchar(255)")
    private String toppingName;

    @Column(name = "toppingPrice")
    private Integer toppingPrice;
}
