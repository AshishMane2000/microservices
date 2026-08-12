package com.prep.user_ms.controller;


import com.prep.user_ms.dto.request.LoginRequest;
import com.prep.user_ms.dto.response.ApiResponse;
import com.prep.user_ms.dto.response.LoginResponse;
import com.prep.user_ms.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
    LoginResponse response = userService.login(loginRequest);



        ApiResponse<LoginResponse> apiResponse =
                ApiResponse.<LoginResponse>builder()
                        .success(true)
                        .message("Login successful")
                        .data(response)
                        .timeStamp(LocalDateTime.now())
                        .build();


return ResponseEntity.ok(apiResponse);
    }

}
