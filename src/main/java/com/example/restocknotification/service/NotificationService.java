package com.example.restocknotification.service;

import com.example.restocknotification.domain.*;
import com.example.restocknotification.repository.ProductNotificationHistoryRepository;
import com.example.restocknotification.repository.ProductRepository;
import com.example.restocknotification.repository.ProductUserNotificationHistoryRepository;
import com.example.restocknotification.repository.ProductUserNotificationRepository;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NotificationService {

    private final ProductRepository productRepository;
    private final HistoryService historyService;
    private final ProductUserNotificationRepository productUserNotificationRepository;
    private final ProductNotificationHistoryRepository productNotificationHistoryRepository;

    private final ProductUserNotificationHistoryRepository productUserNotificationHistoryRepository;
    public NotificationService(ProductRepository productRepository,HistoryService historyService,ProductUserNotificationRepository productUserNotificationRepository
    ,ProductNotificationHistoryRepository productNotificationHistoryRepository
    ,ProductUserNotificationHistoryRepository productUserNotificationHistoryRepository){
        this.productRepository = productRepository;
        this.historyService = historyService;
        this.productUserNotificationRepository = productUserNotificationRepository;
        this.productNotificationHistoryRepository = productNotificationHistoryRepository;
        this.productUserNotificationHistoryRepository = productUserNotificationHistoryRepository;
    }

    //요청이들어오면
    public void sendNotification(Long productId){
        Optional<Product> result = productRepository.findById(productId);
        Product product = result.orElseThrow(()-> new IllegalArgumentException("해당상품없음"));
        //재입고 회차를 1 증가시키고
        product.increaseRestockRound();
        productRepository.save(product);

        //알림을 설정한 유저 순서대로 메시지 전송
        //회차별 재입고 알림을 받은 유저 목록 저장

        //IN_PROGRESS
        ProductNotificationHistory productNotificationHistory = historyService.updateStatus(product);
        //발송할유저찾기 - ProductUserNotification 테이블에 존재하는 유저는 모두 재입고 알림을 설정했다고 가정
        List<ProductUserNotification> users = productUserNotificationRepository.findByProductIdOrderByIdAsc(productId);
        //알람 발송
        //1초에 최대 500개의 요청만 보내도록 제한하기위해 guava 로 rateLimiter 구현
        RateLimiter rateLimiter = RateLimiter.create(500);
        Long lastUserId = null;
        for (ProductUserNotification notification : users){
            try{
                rateLimiter.acquire();
                lastUserId = notification.getUserId();
                //재고가 없어지면 알림 중단
                //재고가 없으면 UNAVAILABLE
                if(product.isUnavailable()) {
                    //CANCELED_BY_SOLD_OUT
                    productNotificationHistory.changeStatus(NotificationStatus.CANCELED_BY_SOLD_OUT);
                    if (lastUserId != null) {
                        productNotificationHistory.setLastUserId(lastUserId);
                    } else {
                        productNotificationHistory.setLastUserId(0L);
                    }
                }
                //재고있어서 알림 발송
                ProductUserNotificationHistory history = new ProductUserNotificationHistory();
                history.create(product,lastUserId);
                productUserNotificationHistoryRepository.save(history);
                productNotificationHistory.setLastUserId(notification.getUserId());
                productNotificationHistoryRepository.save(productNotificationHistory);
                } catch(IllegalStateException e){
                    return;
                }

            }
        //COMPLETED
        if (!product.isUnavailable()){
            productNotificationHistory.changeStatus(NotificationStatus.COMPLETED);
            productNotificationHistoryRepository.save(productNotificationHistory);

         }



        }

}
