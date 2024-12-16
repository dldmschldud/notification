package com.example.restocknotification.domain;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
public class ProductUserNotification extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    private int restockRound;



}
