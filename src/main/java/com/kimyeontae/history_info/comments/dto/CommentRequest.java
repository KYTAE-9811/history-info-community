package com.kimyeontae.history_info.comments.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CommentRequest {

    private String content;

    private Integer likes;
    private Integer dislikes;
}
