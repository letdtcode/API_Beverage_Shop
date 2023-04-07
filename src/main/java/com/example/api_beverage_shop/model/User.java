package com.example.api_beverage_shop.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName", columnDefinition = "nvarchar(255)")
    private String firstName;

    @Column(name = "lastName", columnDefinition = "nvarchar(255)")
    private String lastName;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "mail")
    private String mail;

    @Column(name = "address", columnDefinition = "nvarchar(255)")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "avatar")
    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "Id")},
            inverseJoinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "Id")}
    )
    private Set<Role> roles;

    //Two way mapping
    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy = "userOrder")
    private List<Order> orderList;


}
