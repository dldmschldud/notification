package com.example.restocknotification.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int restockRound;

    @Enumerated(EnumType.STRING)
    private RestockStatus restockStatus;

    public void increaseRestockRound(){
        this.restockRound += 1;
    }

    public boolean isUnavailable(){
        return this.restockStatus == RestockStatus.UNAVAILABLE;
    }

}

