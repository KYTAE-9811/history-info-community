package com.kimyeontae.history_info.posts.dto;
import lombok.Data;

@Data
public class PostRequest {
    private Integer id;
    private String title;
    private String content;
    private String writer;
    private String imageUrl;
}
