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
        model.addAttribute("topic1", topicService.findTopic(1L));
        model.addAttribute("topic2", topicService.findTopic(2L));
        model.addAttribute("topic3", topicService.findTopic(3L));
        model.addAttribute("topic4", topicService.findTopic(4L));

        return "home";
    }

}
