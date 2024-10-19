package com.kimyeontae.history_info.users.dto;

import com.kimyeontae.history_info.Role;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserLoginResponse {
    private String username;
    private String password;
    private String email;
    private String nickname;
    private Role role;
    private LocalDate createdAt;
}
