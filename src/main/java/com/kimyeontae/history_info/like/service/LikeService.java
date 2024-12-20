package com.kimyeontae.history_info.like.service;

import com.kimyeontae.history_info.like.Like;
import com.kimyeontae.history_info.like.dto.LikeRequest;
import com.kimyeontae.history_info.like.repository.LikeRepository;
import com.kimyeontae.history_info.users.Users;
import com.kimyeontae.history_info.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    public void addLike(LikeRequest likeRequest) {
        Users user = userRepository.findById(likeRequest.getUserId()).orElseThrow(() -> new RuntimeException("유저 없음"));

        if (likeRepository.existsByUserAndTargetIdAndTargetType(user, likeRequest.getTargetId(), likeRequest.getTargetType())) {
            throw new RuntimeException("이미 좋아요 됨");
        }

        Like like = new Like(user, likeRequest.getTargetId(), likeRequest.getTargetType());
        likeRepository.save(like);
    }

    public void removeLike(Long userId, Long targetId, String targetType) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없음"));

        likeRepository.deleteByUserAndTargetIdAndTargetType(user, targetId, targetType);
    }

    public long getLikeCount(Long targetIdId, String targetType) {
        return likeRepository.countByTargetIdAndTargetType(targetIdId, targetType);
    }
}
