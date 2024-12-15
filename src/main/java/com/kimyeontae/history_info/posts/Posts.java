package com.kimyeontae.history_info.posts;

import com.kimyeontae.history_info.comments.Comment;
import com.kimyeontae.history_info.like.Like;
import com.kimyeontae.history_info.topic.Topic;
import com.kimyeontae.history_info.users.Users;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Posts {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String writer;

    @Lob
    private String content;
    private String imageUrl;
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Integer views;
    private Integer commentsCount;

    /*
    연관관계 필드
    */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    /*
    생성 메서드
     */
    public void updateWriter(String writer){
        this.writer = writer;
    }

    public Posts(String title, String content, String imageUrl) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public Posts(String title, String content, String imageUrl, String password) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.password = password;
        this.commentsCount = 0;
        this.views = 0;
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
        if (this.commentsCount == null) {
            this.commentsCount = 0;
        }
        this.comments.add(comment);
        this.commentsCount++;
        return this;
    }

    /*
    의미있는 수정 메소드
     */

    public Posts minusComments_count(){
        this.commentsCount--;
        return this;
    }

    public Posts addViews() {
        this.views++;
        return this;
    }

    public void addTopic(Topic topic) {
        this.topic = topic;
    }



}
