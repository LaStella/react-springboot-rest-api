package com.example.coffee.model;

public enum OrderStatus {
    ACCEPTED,
    PAYMENT_CONFIRMED, // 결제 완료
    READY_FOR_DELIVERY, // 배송 준비
    SHIPPED,
    SETTLED, // 배송 도착
    CANCELLED // 주문 취소
}
