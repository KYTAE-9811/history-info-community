package com.kimyeontae.history_info.users.service;

import com.kimyeontae.history_info.Role;
import com.kimyeontae.history_info.users.Users;
import com.kimyeontae.history_info.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users findUser = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("아이디를 찾을 수 없습니다."));

        return new User(findUser.getUsername(), findUser.getPassword(), getAuthorities(Role.USER));
    }
    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
}
