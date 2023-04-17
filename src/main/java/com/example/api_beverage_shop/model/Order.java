package com.example.api_beverage_shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "OrderBill")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "nameCus", columnDefinition = "nvarchar(255)")
    private String nameCustomer;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "address", columnDefinition = "nvarchar(255)")
    private String address;

    @Column(name = "shipping")
    private Integer shipping;

    @Column(name = "payment")
    private Integer payment;

    @Column(name = "totalItemPrice")
    private BigDecimal totalItemPrice;


    @Column(name = "totalPrice")
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "discountId")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userOrder;

//    Two way mapping
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;
}
