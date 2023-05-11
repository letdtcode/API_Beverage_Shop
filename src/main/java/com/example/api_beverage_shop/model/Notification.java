package com.example.api_beverage_shop.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "title", columnDefinition = "nvarchar(255)")
    private String title;

    @Column(name = "content", columnDefinition = "nvarchar(255)")
    private String content;

    @Column(name = "pathImage")
    private String pathImgDescription;
}
