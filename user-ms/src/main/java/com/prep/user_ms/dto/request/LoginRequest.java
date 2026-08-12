package com.prep.user_ms.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest
{
    @NotBlank(message = "Please provide email")
    @Email(message = "Please provide valid email")
    private String email;

    @NotBlank(message = "please provide password")
    private String password;
}
