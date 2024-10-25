package com.kimyeontae.history_info.home;

import com.kimyeontae.history_info.topic.Topic;
import com.kimyeontae.history_info.topic.TopicService;
import com.kimyeontae.history_info.users.CustomUserDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final TopicService topicService;

    @GetMapping
    public String home(Model model) {
        List<Topic> topics = topicService.AllTopics();
        model.addAttribute("topics", topics);

        return "home";
    }

}
