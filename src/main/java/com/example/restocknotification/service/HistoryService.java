package com.example.restocknotification.service;

import com.example.restocknotification.domain.Product;
import com.example.restocknotification.domain.ProductNotificationHistory;
import com.example.restocknotification.repository.ProductNotificationHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HistoryService {

    private final ProductNotificationHistoryRepository productNotificationHistoryRepository;

    public HistoryService(ProductNotificationHistoryRepository productNotificationHistoryRepository){
        this.productNotificationHistoryRepository = productNotificationHistoryRepository;
    }

    ProductNotificationHistory updateStatus(Product product){
        Optional<ProductNotificationHistory> result = productNotificationHistoryRepository.findById(product.getId());
        ProductNotificationHistory history = result.orElseThrow(() -> new IllegalArgumentException("해당상품없음"));
        history.create(product);//IN_PROGRESS
        productNotificationHistoryRepository.save(history);
        return history;
    }



}
