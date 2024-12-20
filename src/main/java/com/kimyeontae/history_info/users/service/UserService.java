package com.kimyeontae.history_info.users.service;

import com.kimyeontae.history_info.users.Users;
import com.kimyeontae.history_info.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long join(Users user) {
        return userRepository.save(user).getId();
    }

    public Long findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow().getId();
    }
}
