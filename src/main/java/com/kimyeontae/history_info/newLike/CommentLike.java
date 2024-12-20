package com.kimyeontae.history_info.newLike;

import com.kimyeontae.history_info.comments.Comment;
import com.kimyeontae.history_info.users.Users;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    private LocalDateTime createdAt = LocalDateTime.now();

    // 생성자, Getter, Setter
}
