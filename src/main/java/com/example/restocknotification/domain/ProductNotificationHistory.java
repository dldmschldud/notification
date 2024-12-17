package com.example.restocknotification.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ProductNotificationHistory {//상품별 재입고 알림히스토리

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int restockRound;

    @Enumerated(value = EnumType.STRING)
    private NotificationStatus notificationStatus;

    private Long lastUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;

    public ProductNotificationHistory(int restockRound,NotificationStatus
                                      notificationStatus, Long lastUserId, Product product){
        this.restockRound = restockRound;
        this.notificationStatus = notificationStatus;
        this.lastUserId = lastUserId;
        this.product = product;

    }
    public ProductNotificationHistory create(Product product){
        return new ProductNotificationHistory(product.getRestockRound(), notificationStatus.IN_PROGRESS,null,product);
    }

    public void changeStatus(NotificationStatus notificationStatus){
        this.notificationStatus = notificationStatus;
    }

    public void setLastUserId(Long lastUserId){
        this.lastUserId = lastUserId;
    }

}
