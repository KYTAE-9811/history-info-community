package com.kimyeontae.history_info.posts.dto;
import com.kimyeontae.history_info.posts.Posts;
import com.kimyeontae.history_info.posts.repository.PostRepository;
import lombok.Data;

@Data
public class PostRequest {
    private String title;
    private String content;
    private String imageUrl;

    public Posts toPosts() {
        Posts post = new Posts(this.getTitle(), this.getContent(), this.getImageUrl());
        return post;
    }
}
