package com.example.restocknotification.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int restockRound;

    @Enumerated(EnumType.STRING)
    private RestockStatus restockStatus;



}

