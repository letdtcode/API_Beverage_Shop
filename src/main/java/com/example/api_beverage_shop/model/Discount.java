package com.example.api_beverage_shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "Discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "discountCode")
    private String discountCode;

    @Column(name = "imageDemo")
    private String imageDemo;

    @Column(name = "dateStart")
    private LocalDate dateStart;

    @Column(name = "dateEnd")
    private LocalDate dateEnd;

    @Column(name = "discountValue")
    private BigDecimal discountValue;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private Integer quantity;
}
