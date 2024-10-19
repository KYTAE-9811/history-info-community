package com.kimyeontae.history_info.comments.dto;

import com.kimyeontae.history_info.comments.Comment;
import com.kimyeontae.history_info.posts.Posts;
import com.kimyeontae.history_info.users.Users;
import lombok.Data;
import org.hibernate.annotations.Comments;

import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private String id;
    private String writer;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Integer likes;
    private Integer dislikes;

    public CommentResponse createCommentResponse(Comment comment) {
        this.content = comment.getContent();
        this.likes = comment.getLikes();
        this.dislikes = comment.getDislikes();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
        return this;
    }
}
