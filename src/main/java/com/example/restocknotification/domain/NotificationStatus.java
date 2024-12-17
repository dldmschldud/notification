package com.example.restocknotification.domain;

public enum NotificationStatus {

    IN_PROGRESS, //발송 중
    CANCELED_BY_SOLD_OUT, //품절에 의한 발송 중단
    CANCELED_BY_ERROR, //예외에 의한 발송 중단- 서드 파티 연동에서의 예외
    COMPLETED, //완료
}
