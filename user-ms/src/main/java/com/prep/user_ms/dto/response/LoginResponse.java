package com.prep.user_ms.dto.response;

import lombok.*;

@Getter
@Builder
public class LoginResponse {
    private String email;
    private String message;
}
