package com.kimyeontae.history_info.users;

import com.kimyeontae.history_info.comments.Comment;
import com.kimyeontae.history_info.posts.Posts;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;


import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
public class Users {
    @Id @GeneratedValue
    private long id;
    private String username;
    private String password;
    private String nickname;
    private String email;

    private LocalDate createdAt;

    @OneToMany(mappedBy = "user")
    private List<Posts> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public Users CreateUser(String username, String password, String nickname, String email, LocalDate createdAt) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.createdAt = LocalDate.now();
        return this;
    }
}
