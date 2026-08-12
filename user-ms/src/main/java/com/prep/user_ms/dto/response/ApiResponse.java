package com.prep.user_ms.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Builder
@Getter
public class ApiResponse <T> {
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timeStamp;

}
