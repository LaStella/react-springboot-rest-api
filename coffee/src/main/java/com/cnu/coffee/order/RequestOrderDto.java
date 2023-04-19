package com.cnu.coffee.order;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RequestOrderDto {
    private String orderId;
    private String memo;
    @NotNull(message = "OrderStatus cannot be null")
    private OrderStatus orderStatus;
    @NotNull(message = "Price cannot be null")
    private LocalDateTime createdAt;
}
