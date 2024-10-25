package com.kimyeontae.history_info.users.service;

import com.kimyeontae.history_info.users.CustomUserDetail;
import com.kimyeontae.history_info.users.Users;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
class CustomUserDetailServiceTest {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private UserService userService;

    @Test
    @WithMockUser("slsl654@naver.com")
    void loadUserByUsername() {

        //when
       UserDetails userDetails = customUserDetailService.loadUserByUsername("slsl654@naver.com");

        System.out.println("userDetails = " + userDetails);
    }
}