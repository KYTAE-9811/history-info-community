package com.kimyeontae.history_info.posts.repository;

import com.kimyeontae.history_info.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts, Long> {
}
