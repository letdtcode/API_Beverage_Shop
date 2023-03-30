package com.example.api_beverage_shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "nameCus", columnDefinition = "nvarchar(255)")
    private String nameCustomer;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "address", columnDefinition = "nvarchar(255)")
    private String address;

    @Column(name = "shipping")
    private int shipping;

    @Column(name = "payment")
    private int payment;

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
