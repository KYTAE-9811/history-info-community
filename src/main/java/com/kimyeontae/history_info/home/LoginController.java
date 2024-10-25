package com.kimyeontae.history_info.home;

import com.kimyeontae.history_info.users.Users;
import com.kimyeontae.history_info.users.dto.UserRegisterRequest;
import com.kimyeontae.history_info.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String login(Model model) {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();

        model.addAttribute("userRegisterRequest", userRegisterRequest);
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @PostMapping ("/register")
    public String register(@ModelAttribute("userRegisterRequest") UserRegisterRequest userRegisterRequest) {
        userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        Users user = userRegisterRequest.ToEntity();

        userService.join(user);
        return "redirect:/login";
    }
}
