package com.kimyeontae.history_info.home;

import com.kimyeontae.history_info.users.dto.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping ("/register")
    public String register(@ModelAttribute UserRegisterRequest userRequest) {

        return "redirect:/login";
    }
}
