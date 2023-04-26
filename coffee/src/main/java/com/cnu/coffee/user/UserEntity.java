package com.cnu.coffee.user;

import com.cnu.coffee.order.Order;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false, unique = true)
    private String encryptedPassword;

//    @OneToMany(mappedBy = "userEntity")
//    private List<Order> orders = new ArrayList<>();

//    public void addOrder(Order order) {
//        order.setUserEntity(this);
//    }
}
