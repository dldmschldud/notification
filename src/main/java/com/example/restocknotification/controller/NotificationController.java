package com.example.restocknotification.controller;

import com.example.restocknotification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/products/{productId}/notifications/re-stock")
    public ResponseEntity<String> sendNotification(@PathVariable("productId")Long productId){
        notificationService.sendNotification(productId);
        return ResponseEntity.ok("알람전송성공");

    }

}
