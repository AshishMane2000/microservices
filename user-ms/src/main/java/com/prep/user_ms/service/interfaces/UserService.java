package com.prep.user_ms.service.interfaces;

import com.prep.user_ms.dto.request.CreateUserRequest;
import com.prep.user_ms.dto.request.LoginRequest;
import com.prep.user_ms.dto.response.LoginResponse;
import com.prep.user_ms.dto.response.UserResponse;

public interface UserService {
    UserResponse register(CreateUserRequest request);
    public LoginResponse login(LoginRequest request);

}
