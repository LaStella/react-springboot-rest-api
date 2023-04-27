package com.cnu.coffee.order;

import com.cnu.coffee.user.ResponseUserDto;
import com.cnu.coffee.user.UserDto;
import com.cnu.coffee.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    OrderService orderService;
    UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrderDto> createOrder(@PathVariable("userId") String userId,
                                                        @RequestBody OrderDto orderDto) {
        orderDto.setUserId(userId);

        ResponseOrderDto createdOrder = orderService.createOrder(orderDto);

        ResponseOrderDto responseOrder = new ResponseOrderDto();
        BeanUtils.copyProperties(createdOrder, responseOrder);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

//    @GetMapping("/{userId}/orders")
//    public ResponseEntity<List<ResponseOrderDto>> createOrder(@PathVariable("userId") String userId) {
//        List<Order> orderList = orderService.getOrdersByUserId(userId);
//
//        List<ResponseOrderDto> result = new ArrayList<>();
//        orderList.forEach(v -> {
//            ResponseOrderDto responseOrder = new ResponseOrderDto();
//            BeanUtils.copyProperties(v, responseOrder);
//            result.add(responseOrder);
//        });
//
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }
}
