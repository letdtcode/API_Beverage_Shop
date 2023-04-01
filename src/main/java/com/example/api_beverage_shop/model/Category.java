package com.example.api_beverage_shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "categoryName", columnDefinition = "nvarchar(255)")
    private String categoryName;

    @Column(name = "description", columnDefinition = "nvarchar(255)")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
