package com.kimyeontae.history_info.users.dto;

import com.kimyeontae.history_info.Role;
import com.kimyeontae.history_info.users.Users;
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

    public Users ToEntity() {
        Users user = new Users(this.email, this.nickname, this.password, Role.USER, this.username);
        return user;
    }
}
