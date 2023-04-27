package com.cnu.coffee.order;

import com.cnu.coffee.product.ProductRepository;
import com.cnu.coffee.user.UserEntity;
import com.cnu.coffee.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class OrderService {
    OrderRepository orderRepository;
    ProductRepository productRepository;
    UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public ResponseOrderDto createOrder(OrderDto orderDto) {
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getUnitPrice() * orderDto.getQuantity());
//        Product OrderedProduct = productRepository.findByProductId(requestOrderDto.getOrderedProductId());
//        requestOrderDto.setUnitPrice(OrderedProduct.getPrice());
        Order order = new Order();
        BeanUtils.copyProperties(orderDto, order);
        order.setUserEntity(userRepository.findByUserId(orderDto.getUserId()));
//        UserEntity userEntity = userRepository.findByUserId(orderDto.getUserId());
//        userEntity.addOrder(order);
//        order.setUserEntity(userEntity);


        Order savedOrder = orderRepository.save(order);

        ResponseOrderDto returnOrderDto = new ResponseOrderDto();
        BeanUtils.copyProperties(savedOrder, returnOrderDto);

        return returnOrderDto;
    }

    public ResponseOrderDto getOrderByOrderId(String orderId) {
        ResponseOrderDto returnValue = new ResponseOrderDto();
        Order order = orderRepository.findByOrderId(orderId);
        BeanUtils.copyProperties(order, returnValue);

        return returnValue;
    }

//    public List<Order> getOrdersByUserId(String userId) {
//        UserEntity userEntity = userRepository.findByUserId(userId);
//        return orderRepository.findByUserEntity(userEntity);
//    }
}
