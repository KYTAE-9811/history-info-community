package com.kimyeontae.history_info.like.repository;

import com.kimyeontae.history_info.like.Like;
import com.kimyeontae.history_info.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndTargetIdAndTargetType(Users user, Long targetId, String targetType);
    void deleteByUserAndTargetIdAndTargetType(Users user, Long targetId, String targetType);
    long countByTargetIdAndTargetType(Long targetId, String targetType);
}
