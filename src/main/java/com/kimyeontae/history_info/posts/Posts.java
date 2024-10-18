package com.kimyeontae.history_info.posts;

import com.kimyeontae.history_info.comments.Comment;
import com.kimyeontae.history_info.users.Users;
import jakarta.persistence.*;
import lombok.Getter;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Posts {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Lob
    private String content;
    private String imageUrl;
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Integer views;
    private Integer likes;
    private Integer dislikes;
    private Integer commentsCount;


    /*
    연관관계 필드
    */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    /*
    생성 메서드
     */
    public Posts(String title, String content, String imageUrl, String password) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    public Posts updatePosts(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
        return this;
    }

    /*
    연관관계 편의 메서드
     */
    public Posts addComment(Comment comment) {
        this.comments.add(comment);
        commentsCount++;
        return this;
    }

    /*
    의미있는 수정 메소드
     */

    public Posts addLikes(){
        this.likes++;
        return this;
    }
    public Posts addDislikes() {
        this.dislikes++;
        return this;
    }

    public Posts minusComments_count(){
        this.commentsCount--;
        return this;
    }

    public Posts addViews() {
        this.views++;
        return this;
    }



}
