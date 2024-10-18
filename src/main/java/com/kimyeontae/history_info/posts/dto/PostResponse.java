package com.kimyeontae.history_info.posts.dto;

import com.kimyeontae.history_info.comments.Comment;
import com.kimyeontae.history_info.posts.Posts;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PostResponse {
    private String id;
    private String title;
    private String content;
    private String imageUrl;
    private Integer like;
    private Integer dislike;
    private Integer view;
    private List<Comment> comments;

    public PostResponse creatPostResponse(Posts posts) {
        this.content = posts.getContent();
        this.dislike = posts.getDislikes();
        this.imageUrl = posts.getImageUrl();
        this.like = posts.getLikes();
        this.view = posts.getViews();
        this.title = posts.getTitle();
        this.comments = posts.getComments();
        return this;
    }
}
