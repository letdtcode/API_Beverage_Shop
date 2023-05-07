package com.example.api_beverage_shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "productName", columnDefinition = "nvarchar(255)")
    private String productName;

    @Column(name = "priceDefault")
    private BigDecimal priceDefault;

    @Column(name = "description", columnDefinition = "longtext")
    private String description;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    //    Two way mapping
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

    @Column(name = "pathImage")
    private String pathImage;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "status")
    private Integer status;
}
