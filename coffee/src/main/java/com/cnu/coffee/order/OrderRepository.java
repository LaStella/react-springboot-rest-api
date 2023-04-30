package com.cnu.coffee.order;

import com.cnu.coffee.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(String orderId);
//    List<Order> findByUserEntity(UserEntity userEntity);
}
