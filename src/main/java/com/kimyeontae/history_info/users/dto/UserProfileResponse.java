package com.kimyeontae.history_info.users.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserProfileResponse {
    @NotEmpty
    private String username;
    @NotEmpty
    private String nickname;
    @NotEmpty
    private String email;
    @NotEmpty
    private LocalDate createdAt;
}
