package com.kimyeontae.history_info.posts.dto;

import com.kimyeontae.history_info.comments.Comment;
import com.kimyeontae.history_info.posts.Posts;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter   
@NoArgsConstructor
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private String writer;

    private Integer like;
    private Integer dislike;
    private Integer view;
    private Integer commentsCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostResponse creatPostResponse(Posts posts) {
        this.id = posts.getId();
        this.content = posts.getContent();
        this.dislike = posts.getDislikes();
        this.imageUrl = posts.getImageUrl();
        this.like = posts.getLikes();
        this.view = posts.getViews();
        this.title = posts.getTitle();
        this.createdAt = posts.getCreatedAt();
        this.updatedAt = posts.getUpdatedAt();
        this.commentsCount = posts.getCommentsCount();
        this.writer = posts.getWriter();
        return this;
    }
    public void updateWriter(String writer){
        this.writer = writer;
    }
}
