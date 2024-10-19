package com.kimyeontae.history_info.users.repository;


import com.kimyeontae.history_info.users.Users;

import java.util.Optional;

public interface CustomUserRepository {
    Optional<Users> findByUsername(String username);
}
