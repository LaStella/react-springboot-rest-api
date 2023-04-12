package com.cnu.coffee.order;

import com.cnu.coffee.product.RequestProductDto;
import com.cnu.coffee.product.ResponseProductDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<ResponseOrderDto> createOrder(@RequestBody RequestOrderDto requestOrderDto) {
        orderService.createOrder(requestOrderDto);

        ResponseOrderDto responseOrderDto = new ResponseOrderDto();
        BeanUtils.copyProperties(requestOrderDto, responseOrderDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrderDto);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<ResponseOrderDto>> getAllOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderByAll());
    }
}
