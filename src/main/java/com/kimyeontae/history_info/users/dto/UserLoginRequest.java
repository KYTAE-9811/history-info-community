package com.kimyeontae.history_info.users.dto;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String username;
    private String password;
}
