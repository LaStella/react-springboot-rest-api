package com.cnu.coffee.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private Environment environment;
    private UserService userService;

    @Autowired
    public UserController(Environment environment, UserService userService) {
        this.environment = environment;
        this.userService = userService;
    }

    @GetMapping("/health_check")
    public String checkStatus() {
        return String.format("It's Working in User Service on PORT %s",
                environment.getProperty("local.server.port"));
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUserDto> createUser(@RequestBody UserDto userDto) {
        ResponseUserDto responseUserDto = userService.createUser(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUserDto);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUserDto>> getUsers() {
        List<UserEntity> userList = userService.getUserByAll();

        List<ResponseUserDto> result = new ArrayList<>();
        // 데이터베이스로부터 가져온 유저리스트 값을 변환합니다.
        userList.forEach(v -> {
            ResponseUserDto responseUserDto = new ResponseUserDto();
            BeanUtils.copyProperties(v, responseUserDto);
            result.add(responseUserDto);
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserEntity> getUsers(@PathVariable("userId") String userId) {
        UserEntity userEntity = userService.getUserByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userEntity);
    }
}
