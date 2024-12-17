package com.example.restocknotification.repository;

import com.example.restocknotification.domain.Product;
import com.example.restocknotification.domain.ProductNotificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductNotificationHistoryRepository extends JpaRepository<ProductNotificationHistory, Long>{

}
