package com.prep.user_ms.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    //    private String email;
//    private String message;
    private String accessToken;
    private String tokenType;
}
