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
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "productName", columnDefinition = "nvarchar(255)")
    private String productName;

    @Column(name = "priceDefault")
    private BigDecimal priceDefault;

    @Column(name = "description", columnDefinition = "nvarchar(MAX) not null")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    //    Two way mapping
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImagesUrl;
}
