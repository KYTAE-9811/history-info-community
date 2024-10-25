package com.kimyeontae.history_info.users;

import com.kimyeontae.history_info.Role;
import com.kimyeontae.history_info.comments.Comment;
import com.kimyeontae.history_info.posts.Posts;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String nickname;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDate createdAt;

    @OneToMany(mappedBy = "user")
    private List<Posts> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public Users CreateUser(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.createdAt = LocalDate.now();
        this.role = Role.USER;
        return this;
    }

    public Users(String email, String nickname, String password, Role role, String username) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
        this.username = username;
        this.createdAt = LocalDate.now();
    }
}
