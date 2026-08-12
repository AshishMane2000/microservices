package com.prep.user_ms.controller;


import com.prep.user_ms.dto.request.CreateUserRequest;
import com.prep.user_ms.dto.response.ApiResponse;
import com.prep.user_ms.dto.response.UserResponse;
import com.prep.user_ms.service.impl.UserServiceImpl;
import com.prep.user_ms.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Ashish!";
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody CreateUserRequest request) {
        UserResponse response = userService.register(request);

        ApiResponse<UserResponse> userResponse = ApiResponse.<UserResponse>builder().success(true)
                .message("User registered successfully")
                .data(response)
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);

    }


}
