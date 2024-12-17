package com.example.restocknotification.repository;

import com.example.restocknotification.domain.ProductUserNotification;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductUserNotificationRepository extends JpaRepository<ProductUserNotification,Long> {
    List<ProductUserNotification> findByProductIdOrderByIdAsc(Long productId);
}
