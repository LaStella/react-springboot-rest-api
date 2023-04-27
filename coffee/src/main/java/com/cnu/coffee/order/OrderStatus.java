package com.cnu.coffee.order;

public enum OrderStatus {
    // 주문완료, 결제완료, 배송준비, 배송중, 배송완료
    ORDER,
    PAYMENT,
    DELIVERY_READY,
    DELIVERY,
    COMPLETE;
}