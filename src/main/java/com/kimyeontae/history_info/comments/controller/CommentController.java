package com.kimyeontae.history_info.comments.controller;

import com.kimyeontae.history_info.Topic;
import com.kimyeontae.history_info.comments.Comment;
import com.kimyeontae.history_info.comments.dto.CommentRequest;
import com.kimyeontae.history_info.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/{topic}/posts/{postId}")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public String addComment(@PathVariable("postId") Long postId, @PathVariable("topic") Topic topic, @ModelAttribute("comment") CommentRequest commentRequest) {
        Comment comment = commentRequest.toComment();
        commentService.addComment(postId, comment);

        return "redirect:{topic}/posts/{postId}";
    }


}
