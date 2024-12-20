package com.kimyeontae.history_info.like.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeRequest {
    private Long userId;
    private Long targetId;
    private String targetType; // "POST" or "COMMENT"
}
