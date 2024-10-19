package com.kimyeontae.history_info.comments;

import com.kimyeontae.history_info.posts.Posts;
import com.kimyeontae.history_info.users.Users;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Integer likes;
    private Integer dislikes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Posts post;

    /*
    생성 메서드
    */
    public Comment createComment(String content){
        this.createdAt = LocalDateTime.now();
        this.content = content;
        return this;
    }
    public Comment updateComment(String content){
        this.content = content;
        this.updatedAt = LocalDateTime.now();
        return this;
    }
}
