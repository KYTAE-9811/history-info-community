package com.kimyeontae.history_info.comments.dto;

import com.kimyeontae.history_info.comments.Comment;
import com.kimyeontae.history_info.posts.Posts;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CommentRequest {

    private String content;

    public Comment toComment() {
        Comment comment = new Comment(
                this.getContent()
        );
        return comment;
    }
}
