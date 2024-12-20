package com.kimyeontae.history_info.like.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeResponse {
    private Long targetId;
    private String targetType; // "POST" or "COMMENT"
    private boolean isLiked;   // 현재 사용자가 좋아요를 눌렀는지
    private long likeCount;    // 총 좋아요 수
}
