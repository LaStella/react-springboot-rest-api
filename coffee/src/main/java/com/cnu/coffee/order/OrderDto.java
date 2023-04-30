package com.cnu.coffee.order;

import lombok.Data;

@Data
public class OrderDto {
    private String orderId;
    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;
    private String productId;
    //    private OrderStatus status;
    private String userId;
//    private Date createdAt;
}
