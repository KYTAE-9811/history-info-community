package com.kimyeontae.history_info.users.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserLoginRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
