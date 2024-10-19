package com.kimyeontae.history_info.users.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserRegisterRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String nickname;
    @NotEmpty
    private String email;
}
