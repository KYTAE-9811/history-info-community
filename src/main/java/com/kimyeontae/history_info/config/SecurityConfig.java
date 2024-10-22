package com.kimyeontae.history_info.config;

import com.kimyeontae.history_info.Role;
import com.kimyeontae.history_info.users.Users;
import com.kimyeontae.history_info.users.repository.UserRepository;
import com.kimyeontae.history_info.users.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/login", "/register", "/css/**", "/js/**", "/", "/assets/**", "/images/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("username")
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());
        return http.build();
    }

//    @Bean
//    public CommandLineRunner setupAdminUser(UserRepository userRepository) {
//        return args -> {
//            if (!userRepository.findByUsername("admin9811").isPresent()) {
//                Users admin = new Users("admin.com", "admin", "1234", Role.ADMIN, "admin9811");userRepository.save(admin);
//            }
//        };
//    }

}
