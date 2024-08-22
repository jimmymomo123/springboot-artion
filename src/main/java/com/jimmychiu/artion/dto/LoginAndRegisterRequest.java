package com.jimmychiu.artion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginAndRegisterRequest {

    @NotBlank(message = "帳號不能為空值")
    @Size(min = 5, max = 20, message = "帳號長度必須介於 5 到 20 個字符")
    @Schema(description = "帳號", example = "johndoe", minLength = 5, maxLength = 20, required = true)
    private String username;

    @NotBlank(message = "密碼不能為空值")
    @Size(max = 50, message = "密碼長度不能超過 50 個字符")
    @Schema(description = "密碼", example = "password123", maxLength = 50)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
