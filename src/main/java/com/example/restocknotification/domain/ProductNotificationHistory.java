package com.example.restocknotification.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class ProductNotificationHistory {//상품별 재입고 알림 히스토리

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int restockRound;

    @Enumerated(value = EnumType.STRING)
    private NotificationStatus notificationStatus;

    private Long lastUserId;
}
