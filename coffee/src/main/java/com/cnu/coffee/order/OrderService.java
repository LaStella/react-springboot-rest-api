package com.cnu.coffee.order;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(RequestOrderDto requestOrderDto) {
        requestOrderDto.setOrderId(UUID.randomUUID().toString());

        Order order = new Order();
        BeanUtils.copyProperties(requestOrderDto, order);

        return orderRepository.save(order);
    }


    public List<ResponseOrderDto> getOrderByAll() {
        List<Order> orderList = orderRepository.findAll();

        List<ResponseOrderDto> result = new ArrayList<>();
        orderList.forEach(v -> {
            ResponseOrderDto responseOrderDto = new ResponseOrderDto();
            BeanUtils.copyProperties(v, responseOrderDto);
            result.add(responseOrderDto);
        });

        return result;
    }
}
