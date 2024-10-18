package com.kimyeontae.history_info;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @PostMapping("/")
    public String home(Model model) {
        TestPost post = new TestPost("안녕하세용", 1 , "서브타이틀", "제목");
        post.setDate();
        model.addAttribute("posts", post);
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping ("/post")
    public String post() {
        return "post";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
