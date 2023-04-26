package com.cnu.coffee.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    ResponseUserDto createUser(UserDto userDto);

    ResponseUserDto getUserByUserId(String userId);

    List<UserEntity> getUserByAll();

    UserDto getUserDetailsByEmail(String email);
}
