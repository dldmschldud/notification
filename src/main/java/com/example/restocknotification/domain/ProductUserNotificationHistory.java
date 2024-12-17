package com.example.restocknotification.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class ProductUserNotificationHistory {//상품 + 유저별 알림 히스토리
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    private LocalDateTime sentAt = LocalDateTime.now();

    private int restockRound;

    public ProductUserNotificationHistory(Long user_id, Product product, LocalDateTime sentAt,int restockRound) {
        this.user_id = user_id;
        this.product = product;
        this.sentAt = sentAt;
        this.restockRound = restockRound;

    }
    public ProductUserNotificationHistory create(Product product,Long user_id){

        return new ProductUserNotificationHistory(user_id,product,LocalDateTime.now(),product.getRestockRound());

    }
}
