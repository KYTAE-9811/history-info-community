package com.kimyeontae.history_info.posts.repository;

import com.kimyeontae.history_info.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> {
    List<Posts> findByTopicId(Long topicId);
}
